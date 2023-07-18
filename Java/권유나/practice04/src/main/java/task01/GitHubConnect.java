package task01;

import org.kohsuke.github.*;

import java.io.IOException;


public class GitHubConnect {

    private static GitHubConnect connect = new GitHubConnect();
    private static GitHub github;

    public GitHubConnect() {
        String path = "src/main/resources/application.properties";

        try {
            github = new GitHubBuilder().fromPropertyFile(path).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GitHub getConnection() {
        return github;
    }

}
