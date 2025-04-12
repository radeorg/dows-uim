package org.dows.uim.one;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.RemoteRefUpdate;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GitBatchProcessor {

    private static final Pattern NUMBERED_FILE_PATTERN = Pattern.compile("^\\d+\\.txt$");
    private static final int DEFAULT_DELAY_SECONDS = 300;
    
    // 配置您的 GitHub 凭据或 SSH 密钥信息
    private static final String GITHUB_USERNAME = "lait.zhang@gmail.com";
    private static final String GITHUB_PASSWORD = "githubz123!";
    private static final String SSH_PRIVATE_KEY_PATH = System.getProperty("user.home") + "/.ssh/id_ecdsa";
    private static final String SSH_PASSPHRASE = ""; // 如果没有密码短语，设为 null 或空字符串

    /*static {
        // 初始化 SSH 会话工厂
        SshSessionFactory.setInstance(new JschConfigSessionFactory() {
            @Override
            protected void configure(OpenSshConfig.Host host, Session session) {
                // 配置 SSH 参数
                session.setConfig("StrictHostKeyChecking", "no");
            }

            @Override
            protected JSch createDefaultJSch(FS fs) throws JSchException {
                JSch jsch = super.createDefaultJSch(fs);
                try {
                    // 添加 SSH 私钥
                    byte[] privateKey = Files.readAllBytes(new File(SSH_PRIVATE_KEY_PATH).toPath());
                    jsch.addIdentity("github-ssh-key", 
                                    privateKey, 
                                    null, 
                                    SSH_PASSPHRASE != null ? SSH_PASSPHRASE.getBytes() : null);
                } catch (IOException e) {
                    throw new JSchException("无法读取 SSH 私钥文件: " + SSH_PRIVATE_KEY_PATH, e);
                }
                return jsch;
            }
        });
    }*/

    public static void main(String[] args) {
        String rootDir = "D:/workspaces/java/projects/rade"; // 修改为您的实际目录
        processProjects(new File(rootDir));
    }

    private static void processProjects(File rootDir) {
        List<ProjectInfo> projects = findGitProjectsWithNumberedFiles(rootDir);

        if (projects.isEmpty()) {
            System.out.println("未找到任何包含数字.txt文件的Git项目");
            return;
        }

        System.out.println("找到 " + projects.size() + " 个项目需要处理:");
        projects.forEach(p -> System.out.println(p.getOrder() + ". " + p.getProjectDir().getName() +
                " (等待: " + p.getDelaySeconds() + "秒)"));

        for (ProjectInfo project : projects) {
            System.out.println("\n=====================================");
            System.out.println("正在处理项目[" + project.getOrder() + "]: " + project.getProjectDir().getName());
            System.out.println("等待时间: " + project.getDelaySeconds() + " 秒");
            System.out.println("=====================================");

            try (Repository repository = getRepository(project.getProjectDir())) {
                if (repository == null) {
                    System.out.println("错误: 不是有效的Git仓库 - " + project.getProjectDir().getAbsolutePath());
                    continue;
                }

                processGitRepository(new Git(repository));
            } catch (Exception e) {
                System.out.println("处理项目 " + project.getProjectDir().getName() + " 时出错: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("操作完成，等待 " + project.getDelaySeconds() + " 秒后继续...");
            try {
                TimeUnit.SECONDS.sleep(project.getDelaySeconds());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("等待被中断");
                break;
            }
        }

        System.out.println("\n所有项目处理完成!");
    }

    private static List<ProjectInfo> findGitProjectsWithNumberedFiles(File rootDir) {
        try {
            return Files.walk(rootDir.toPath(), 1)
                    .filter(path -> {
                        File dir = path.toFile();
                        return dir.isDirectory() && new File(dir, ".git").exists();
                    })
                    .flatMap(projectDir -> {
                        try {
                            return Files.list(projectDir)
                                    .filter(file -> NUMBERED_FILE_PATTERN.matcher(file.getFileName().toString()).matches())
                                    .map(file -> {
                                        int order = Integer.parseInt(file.getFileName().toString().replace(".txt", ""));
                                        int delay = readDelayTime(file.toFile());
                                        return new ProjectInfo(order, delay, projectDir.toFile());
                                    });
                        } catch (IOException e) {
                            return Stream.empty();
                        }
                    })
                    .sorted(Comparator.comparingInt(ProjectInfo::getOrder))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("读取目录失败: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static int readDelayTime(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && !line.trim().isEmpty()) {
                return Integer.parseInt(line.trim());
            }
        } catch (Exception e) {
            System.out.println("读取等待时间失败，使用默认值 " + DEFAULT_DELAY_SECONDS + " 秒");
        }
        return DEFAULT_DELAY_SECONDS;
    }

    private static Repository getRepository(File projectDir) throws IOException {
        return new FileRepositoryBuilder()
                .setGitDir(new File(projectDir, ".git"))
                .readEnvironment()
                .findGitDir()
                .build();
    }

    private static void processGitRepository(Git git) throws GitAPIException, IOException {
        String currentBranch = git.getRepository().getBranch();
        System.out.println("当前分支: " + currentBranch);

        if ("master".equalsIgnoreCase(currentBranch) || "main".equalsIgnoreCase(currentBranch)) {
            System.out.println("跳过 master/main 分支");
            return;
        }

        String targetBranch = determineTargetBranch(currentBranch);
        if (targetBranch == null) {
            System.out.println("无法识别的分支格式: " + currentBranch);
            return;
        }

        if (!branchExists(git, targetBranch)) {
            System.out.println("目标分支不存在: " + targetBranch);
            return;
        }

        commitChanges(git);
        switchAndMerge(git, currentBranch, targetBranch);
        checkoutBranch(git, currentBranch);
    }

    private static String determineTargetBranch(String currentBranch) {
        if (currentBranch.startsWith("sit-")) {
            return "dev-" + currentBranch.substring(4);
        } else if (currentBranch.startsWith("dev-")) {
            return "sit-" + currentBranch.substring(4);
        }
        return null;
    }

    private static boolean branchExists(Git git, String branchName) throws GitAPIException {
        return git.branchList().call().stream()
                .anyMatch(ref -> ref.getName().equals("refs/heads/" + branchName));
    }

    private static void commitChanges(Git git) throws GitAPIException {
        Status status = git.status().call();

        if (!status.getAdded().isEmpty() || !status.getChanged().isEmpty() || !status.getModified().isEmpty()) {
            System.out.println("提交当前更改...");
            git.add().addFilepattern(".").call();
            git.commit().setMessage("自动提交: 合并前的更改").call();
        } else {
            System.out.println("没有需要提交的更改");
        }
    }

    private static void switchAndMerge(Git git, String sourceBranch, String targetBranch) throws GitAPIException {
        System.out.println("切换到目标分支 " + targetBranch + "...");
        checkoutBranch(git, targetBranch);

        System.out.println("合并 " + sourceBranch + " 到 " + targetBranch + "...");
        try {
            MergeResult mergeResult = git.merge()
                    .include(git.getRepository().findRef("refs/heads/" + sourceBranch))
                    .setCommit(true)
                    .setMessage("自动合并: 从 " + sourceBranch + " 合并到 " + targetBranch)
                    .call();
            
            if (mergeResult.getMergeStatus().isSuccessful()) {
                System.out.println("合并成功，推送更改...");
                pushToGitHub(git, targetBranch);
            } else {
                System.out.println("合并失败，状态: " + mergeResult.getMergeStatus());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void pushToGitHub(Git git, String branchName) throws GitAPIException {
        PushCommand pushCommand = git.push()
                .setRemote("origin")
                //.setRefSpecs(new RefSpec(String.format("refs/heads/%s:refs/heads/%s", branchName, branchName)))
                .add(branchName)
                .setForce(false); // 谨慎使用强制推送
        
        // 如果使用 HTTPS 而非 SSH，需要设置凭据
        pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(GITHUB_USERNAME, GITHUB_PASSWORD));
        
        try {
            Iterable<PushResult> results = pushCommand.call();
            for (PushResult result : results) {
                for (RemoteRefUpdate update : result.getRemoteUpdates()) {
                    System.out.println("推送状态: " + update.getStatus());
                    if (update.getStatus() != RemoteRefUpdate.Status.OK) {
                        System.out.println("推送失败原因: " + update.getMessage());
                    }
                }
            }
            System.out.println("推送成功完成");
        } catch (Exception e) {
            System.out.println("推送失败: " + e.getMessage());
            throw e;
        }
    }

    private static void checkoutBranch(Git git, String branchName) throws GitAPIException {
        git.checkout()
                .setName(branchName)
                .call();
    }

    private static class ProjectInfo {
        private final int order;
        private final int delaySeconds;
        private final File projectDir;

        public ProjectInfo(int order, int delaySeconds, File projectDir) {
            this.order = order;
            this.delaySeconds = delaySeconds;
            this.projectDir = projectDir;
        }

        public int getOrder() {
            return order;
        }

        public int getDelaySeconds() {
            return delaySeconds;
        }

        public File getProjectDir() {
            return projectDir;
        }
    }
}