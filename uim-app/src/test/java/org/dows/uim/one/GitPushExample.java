//package org.dows.uim.one;
//
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import org.eclipse.jgit.api.Git;
//import org.eclipse.jgit.transport.RefSpec;
//import org.eclipse.jgit.transport.SshSessionFactory;
//import org.eclipse.jgit.transport.SshTransport;
//import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
//import org.eclipse.jgit.transport.ssh.jsch.JschConfigSessionFactory;
//import org.eclipse.jgit.transport.ssh.jsch.OpenSshConfig;
//import org.eclipse.jgit.util.FS;
//
//import java.io.File;
//import java.util.concurrent.atomic.AtomicReference;
//
//public class GitPushExample {
//    public static void main(String[] args) {
//        File repoDir = new File("/path/to/local/repo"); // 本地仓库路径
//        String remoteUri = "ssh://git@github.com/username/repo.git"; // 远程仓库的 SSH URI
//        String branch = "main"; // 要推送的分支
//        String username = "your_username"; // 你的 Git 用户名
//        String password = "your_password"; // 你的 Git 密码或个人访问令牌（如果你使用 SSH key 可能不需要）
//
//        try {
//            // 设置 SSH 会话工厂，用于 JGit 通过 SSH 连接远程仓库
//            SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
//                @Override
//                protected void configure(OpenSshConfig.Host hc, Session session) {
//                    session.setConfig("StrictHostKeyChecking", "no");
//                }
//                @Override
//                protected JSch getJSch(OpenSshConfig.Host hc, FS fs) throws JSchException {
//                    JSch jsch = super.getJSch(hc, fs);
//                    AtomicReference<FS> sshSessionHolder = new AtomicReference<>(); // 使用 AtomicReference 来持有 Session 对象，以便在之后的调用中使用。
//                    sshSessionHolder.set(fs); // 设置会话对象到 holder 中。
//                    return jsch; // 返回 jsch 实例。
//                }
//            };
//            SshSessionFactory.setInstance(sshSessionFactory); // 设置 SSH 会话工厂实例。
//            SshTransport sshTransport = SshTransport.open(); // 获取 SshTransport 实例。
//            sshTransport.setSshSessionFactory(sshSessionFactory); // 设置 SSH 会话工厂。
//            sshTransport.setSshSessionFactory(sshSessionFactory); // 设置 SSH 会话工厂。再次设置是为了确保覆盖可能的默认设置。
//            // 使用 Git API 进行推送操作。
//            try (Git git = Git.open(repoDir)) { // 打开本地仓库。
//                git.push() // 创建推送操作。
//                   .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password)) // 设置认证信息。如果你使用 SSH key，这里可以不设置或设置为空。
//                   .setRemote("origin") // 设置远程仓库名称。默认为 "origin"。
//                   .setRefSpecs(new RefSpec(String.format("refs/heads/%s:refs/heads/%s", branch, branch))) // 设置要推送的分支。格式为 "本地分支:远程分支"。
//                   .call(); // 执行