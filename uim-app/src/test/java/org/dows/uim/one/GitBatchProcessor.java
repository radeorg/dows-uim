package org.dows.uim.one;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.RemoteRefUpdate;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.ssh.jsch.JschConfigSessionFactory;
import org.eclipse.jgit.transport.ssh.jsch.OpenSshConfig;
import org.eclipse.jgit.util.FS;

import java.io.*;
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
    private static final String SSH_PRIVATE_KEY_PATH = System.getProperty("user.home") + "/.ssh/id_ecdsa";
    private static final String SSH_PUBLIC_KEY_PATH = System.getProperty("user.home") + "/.ssh/id_ecdsa.pub";
    private static final String SSH_PASSPHRASE = null; // 如果没有密码短语，设为 null 或空字符串

    static {
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
                    byte[] publicKey = Files.readAllBytes(new File(SSH_PUBLIC_KEY_PATH).toPath());
                    jsch.addIdentity("github-ssh-key", privateKey, publicKey, SSH_PASSPHRASE != null ? SSH_PASSPHRASE.getBytes() : null);
                } catch (IOException e) {
                    throw new JSchException("无法读取 SSH 私钥文件: " + SSH_PRIVATE_KEY_PATH, e);
                }
                return jsch;
            }
        });
    }

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
        // 提交修改
        commitChangesAndPull(git);
        // 将当前分支合并到目标分支,然后执行：先pull,再merge,最后push
        switchAndMerge(git, currentBranch, targetBranch);
        // 切换回原始分支
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

    private static void commitChangesAndPull(Git git) throws GitAPIException {
        Status status = git.status().call();

        if (!status.getAdded().isEmpty() || !status.getChanged().isEmpty() || !status.getModified().isEmpty()) {
            System.out.println("提交当前更改...");
            git.add().addFilepattern(".").call();
            git.commit().setMessage("自动提交: 合并前的更改").call();
        } else {
            System.out.println("没有需要提交的更改");
        }
        // 执行 git pull
        PullCommand pullCommand = git.pull();
        PullResult pullResult = pullCommand.call();
        if (pullResult.isSuccessful()) {
            System.out.println("拉取成功");
        } else {
            System.err.println("拉取失败: " + pullResult.getMergeResult().getMergeStatus());
        }
    }

    private static void switchAndMerge(Git git, String sourceBranch, String targetBranch) throws GitAPIException {
        System.out.println("切换到目标分支 " + targetBranch + "...");
        checkoutBranch(git, targetBranch);

        // 检查远程分支是否存在
        boolean remoteBranchExists = remoteBranchExists(git, targetBranch);
        if (!remoteBranchExists) {
            System.out.println("远程分支 " + targetBranch + " 不存在，跳过 pull 操作");
        } else {
            // 执行 git pull
            PullCommand pullCommand = git.pull();
            PullResult pullResult = pullCommand.call();
            if (pullResult.isSuccessful()) {
                System.out.println("拉取成功");
            } else {
                System.err.println("拉取失败: " + pullResult.getMergeResult().getMergeStatus());
            }
        }

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

    private static boolean remoteBranchExists(Git git, String branchName) throws GitAPIException {
        return git.lsRemote().call().stream()
                .anyMatch(ref -> ref.getName().equals("refs/heads/" + branchName));
    }

    private static void pushToGitHub(Git git, String branchName) throws GitAPIException {
        String absolutePath = git.getRepository().getDirectory().getParent();
        System.out.println("projectDir: " + absolutePath);


        // 执行 git push
        PushCommand pushCommand = git.push()
                .setRemote("origin")
                //.setRefSpecs(new RefSpec(String.format("refs/heads/%s:refs/heads/%s", branchName, branchName)))
                .add(branchName)
                .setForce(false); // 谨慎使用强制推送
        
        // 如果使用 HTTPS 而非 SSH，需要设置凭据
        //pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(GITHUB_USERNAME, GITHUB_PASSWORD));
        
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
    private static void printProcessOutput(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        try {
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void checkoutBranch(Git git, String branchName) throws GitAPIException {
        System.out.println("切换到分支 " + branchName + "...");
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