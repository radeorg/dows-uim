package org.dows.uim.one;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.RemoteRefUpdate;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.transport.ssh.jsch.JschConfigSessionFactory;
import org.eclipse.jgit.transport.ssh.jsch.OpenSshConfig;
import org.eclipse.jgit.util.FS;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GitBatchProcessor1 {

    private final String rootDir;
    private final Map<String, String> branchMappings;
    private final UsernamePasswordCredentialsProvider credentialsProvider;
    private final boolean dryRun;

    public GitBatchProcessor1(String rootDir, String branchMappingRules, String username, String password, boolean dryRun) {
        this.rootDir = rootDir;
        this.branchMappings = parseBranchMappings(branchMappingRules);
        this.credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
        this.dryRun = dryRun;
        initSshSessionFactory();  // 启用SSH会话工厂初始化
    }

    private void initSshSessionFactory() {
        SshSessionFactory.setInstance(new JschConfigSessionFactory() {
            @Override
            protected void configure(OpenSshConfig.Host host, Session session) {
                // 配置SSH参数
                session.setConfig("PreferredAuthentications", "publickey");
                session.setConfig("StrictHostKeyChecking", "no");
            }

            @Override
            protected JSch createDefaultJSch(FS fs) throws JSchException {
                JSch jsch = super.createDefaultJSch(fs);
                // 加载默认的SSH私钥
                String privateKeyPath = System.getProperty("user.home") + "/.ssh/id_ecdsa";
                try {
                    jsch.addIdentity(privateKeyPath, null, null);
                } catch (JSchException e) {
                    System.err.println("无法加载SSH私钥: " + privateKeyPath);
                    throw e;
                }
                return jsch;
            }
        });
    }

    private Map<String, String> parseBranchMappings(String rules) {
        Map<String, String> mappings = new HashMap<>();
        if (rules == null || rules.trim().isEmpty()) {
            return mappings;
        }

        String[] pairs = rules.split(",");
        for (String pair : pairs) {
            String[] parts = pair.split("->");
            if (parts.length == 2) {
                mappings.put(parts[0].trim(), parts[1].trim());
            }
        }
        return mappings;
    }

    public void processAllProjects() throws IOException, InterruptedException {
        File root = new File(rootDir);
        if (!root.exists() || !root.isDirectory()) {
            System.err.println("Root directory does not exist or is not a directory: " + rootDir);
            return;
        }

        // 获取所有项目并按顺序文件排序
        List<File> projects = getOrderedProjects(root);

        System.out.println("分支映射规则: " + branchMappings);
        System.out.println("=".repeat(50));

        for (int i = 0; i < projects.size(); i++) {
            File projectDir = projects.get(i);
            System.out.println("正在处理项目[" + (i + 1) + "]: " + projectDir.getName());
            System.out.println("=".repeat(50));

            try {
                String projectPath = projectDir.getAbsolutePath();
                processProject(projectDir);
            } catch (Exception e) {
                System.err.println("处理项目 " + projectDir.getName() + " 时出错: " + e.getMessage());
                e.printStackTrace();
            }

            // 如果不是最后一个项目，则等待
            if (i < projects.size() - 1) {
                int waitTime = getWaitTime(projectDir);
                System.out.println("等待 " + waitTime + " 秒...");
                TimeUnit.SECONDS.sleep(waitTime);
            }
        }
    }

    private List<File> getOrderedProjects(File root) throws IOException {
        List<File> projects = new ArrayList<>();
        File[] files = root.listFiles();
        if (files == null) {
            return projects;
        }

        // 收集所有项目目录（包含.git目录的）
        for (File file : files) {
            if (file.isDirectory() && new File(file, ".git").exists()) {
                projects.add(file);
            }
        }

        // 按顺序文件排序
        projects.sort((p1, p2) -> {
            int n1 = getOrderNumber(p1);
            int n2 = getOrderNumber(p2);
            return Integer.compare(n1, n2);
        });

        return projects;
    }

    private int getOrderNumber(File projectDir) {
        File[] files = projectDir.listFiles();
        if (files == null) {
            return Integer.MAX_VALUE;
        }

        for (File file : files) {
            String name = file.getName();
            if (name.matches("\\d+\\.txt")) {
                try {
                    String content = Files.readString(file.toPath(), StandardCharsets.UTF_8).trim();
                    return Integer.parseInt(content);
                } catch (Exception e) {
                    System.err.println("无法读取顺序文件: " + file.getAbsolutePath());
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private int getWaitTime(File projectDir) throws IOException {
        File[] files = projectDir.listFiles();
        if (files == null) {
            return 0;
        }

        for (File file : files) {
            String name = file.getName();
            if (name.matches("\\d+\\.txt")) {
                try {
                    String content = Files.readString(file.toPath(), StandardCharsets.UTF_8).trim();
                    return Integer.parseInt(content);
                } catch (Exception e) {
                    System.err.println("无法读取等待时间文件: " + file.getAbsolutePath());
                }
            }
        }
        return 0; // 默认不等待
    }

    private void processProject(File projectDir) throws IOException, GitAPIException {

        Git git = Git.open(projectDir);
//        // 2.热行git add命令
//        git.add().addFilepattern(".").call();
//        System.out.println("Added files to the staging area.");
//        //3.提交更改
//        git.commit().setMessage("Your commit message").call();
//        System.out.println("committed changes.");
        // 4、推送到远程
        /*git.push()
                .setCredentialsProvider(credentialsProvider)
                .setRemote(remoteUrl)
                .call();*/

        //try (Repository repository = getRepository(projectDir); Git git = new Git(repository)) {

        // 检查是否有未提交的更改
        Status status = git.status().call();
        if (!status.isClean()) {
            System.out.println("发现未提交的更改，正在提交...");
            git.add().addFilepattern(".").call();
            git.commit().setMessage("Auto commit before branch operations").call();
        }

        Repository repository = git.getRepository();
        // 获取当前分支
        String currentBranch = repository.getBranch();
        System.out.println("当前分支: " + currentBranch);

        // 查找匹配的分支映射规则
        BranchMapping mapping = findMatchingBranchMapping(currentBranch);
        if (mapping == null) {
            System.out.println("没有找到匹配的分支映射规则，跳过此项目");
            return;
        }

        System.out.println("应用分支映射: " + mapping.source + " -> " + mapping.target);

        // 检查目标分支是否存在
        boolean targetBranchExists = branchExists(git, mapping.target);

        // 切换到目标分支（不存在则创建）
        checkoutOrCreateBranch(git, mapping.target, targetBranchExists);

        // 合并源分支到目标分支
        mergeBranch(git, mapping.source);

        // 推送目标分支
        pushBranch(git, mapping.target);

        // 切换回原始分支
        git.checkout().setName(mapping.source).call();
        System.out.println("切换回原始分支 " + mapping.source);
        //}
    }

    private Repository getRepository(File projectDir) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder.setGitDir(new File(projectDir, ".git"))
                .readEnvironment()
                .findGitDir()
                .build();
    }

    private boolean branchExists(Git git, String branchName) throws GitAPIException {
        return git.branchList().call().stream()
                .anyMatch(ref -> ref.getName().equals(Constants.R_HEADS + branchName));
    }

    private void checkoutOrCreateBranch(Git git, String branchName, boolean branchExists) throws GitAPIException {
        if (branchExists) {
            System.out.println("切换到目标分支 " + branchName + "...");
            git.checkout().setName(branchName).call();
        } else {
            System.out.println("创建并切换到目标分支 " + branchName + "...");
            git.checkout().setCreateBranch(true).setName(branchName).call();
        }
    }

    private void mergeBranch(Git git, String sourceBranch) throws GitAPIException {
        System.out.println("合并 " + sourceBranch + " 到当前分支...");
        try {
            MergeResult mergeResult = git.merge()
                    .include(git.getRepository().findRef(Constants.R_HEADS + sourceBranch))
                    .setCommit(true)
                    .setMessage("Merge " + sourceBranch + " into current branch")
                    .call();

            if (mergeResult.getMergeStatus().isSuccessful()) {
                System.out.println("合并成功");
            } else {
                System.err.println("合并失败: " + mergeResult.getMergeStatus());
                throw new RuntimeException("合并失败: " + mergeResult.getMergeStatus());
            }
        } catch (GitAPIException | IOException e) {
            System.err.println("合并失败: " + e.getMessage());
        }
    }

    private void pushBranch(Git git, String branchName) throws GitAPIException {
        if (dryRun) {
            System.out.println("[DRY RUN] 跳过推送分支 " + branchName);
            return;
        }

        System.out.println("推送目标分支 " + branchName + "...");
        PushCommand pushCommand = git.push()
                .setRemote("origin")
                .add(branchName)
                .setCredentialsProvider(credentialsProvider);

        Iterable<PushResult> pushResults = pushCommand.call();
        for (PushResult pushResult : pushResults) {
            for (RemoteRefUpdate update : pushResult.getRemoteUpdates()) {
                System.out.println("推送状态: " + update.getStatus());
                if (update.getStatus() != RemoteRefUpdate.Status.OK) {
                    throw new RuntimeException("推送失败: " + update.getMessage());
                }
            }
        }
        System.out.println("推送成功");
    }

    private BranchMapping findMatchingBranchMapping(String currentBranch) {
        // 1. 首先尝试精确匹配
        if (branchMappings.containsKey(currentBranch)) {
            return new BranchMapping(currentBranch, branchMappings.get(currentBranch));
        }

        // 2. 尝试前缀匹配
        for (Map.Entry<String, String> entry : branchMappings.entrySet()) {
            String sourcePattern = entry.getKey();
            if (currentBranch.startsWith(sourcePattern + "-")) {
                String targetPrefix = entry.getValue();
                String suffix = currentBranch.substring(sourcePattern.length());
                return new BranchMapping(currentBranch, targetPrefix + suffix);
            }
        }

        return null;
    }

    private static class BranchMapping {
        final String source;
        final String target;

        BranchMapping(String source, String target) {
            this.source = source;
            this.target = target;
        }

        @Override
        public String toString() {
            return source + " -> " + target;
        }
    }

    public static void main(String[] args) {
        // 示例参数
        String rootDir = "D:/workspaces/java/projects/rade";
        String branchMappingRules = "dev->sit,sit->dev";
        String username = "lait.zhang@gmail.com";
        String password = "githubz123!";
        boolean dryRun = false;

        // 从命令行参数获取值
        if (args.length >= 4) {
            rootDir = args[0];
            branchMappingRules = args[1];
            username = args[2];
            password = args[3];
            dryRun = args.length > 4 && Boolean.parseBoolean(args[4]);
        }

        GitBatchProcessor1 processor = new GitBatchProcessor1(rootDir, branchMappingRules, username, password, dryRun);
        try {
            processor.processAllProjects();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}