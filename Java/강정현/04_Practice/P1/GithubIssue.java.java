package org.example.practice04;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 깃헙 이슈 1번부터 7번까지 댓글을 순회하며, 댓글을 남긴 사용자 체크하여 참여율 계산.
 * 총 7회 중 몇 %를 참여했는지, 소숫점 두 자리 보여줌.
 * Github 자바 라이브러리 사용.
 * 코드 출처: https://wonyong-jang.github.io/git/2020/12/08/Github-API-for-java.html
 */

public class GithubIssue {
    static final int TOTAL_ISSUE = 7;
    static List<GHIssue> issues;
    static HashMap<GHUser, boolean[]> map;
    public static void main(String[] args) throws Exception {
        String path = "src/main/resources/application.properties";
        GitHub github = GitHubBuilder.fromPropertyFile(path).build();

        GHRepository repository = github.getRepository("NewSainTurtle/CS-study");
        issues = repository.getIssues(GHIssueState.ALL);
        map = new HashMap<>();

        for (int i = 0; i < issues.size(); i++) {
            List<GHIssueComment> comments = issues.get(i).getComments();
            for (GHIssueComment comment : comments) {
                GHUser user = comment.getUser();
                boolean[] attendance = new boolean[TOTAL_ISSUE];
                if (map.containsKey(user)) attendance = map.get(user);
                attendance[i] = true;
                map.put(user, attendance);
            }
        }
        NumOfParticipation();
    }

    public static void NumOfParticipation () throws Exception {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<GHUser, boolean[]> cur : map.entrySet()) {
            int sum = 0;
            boolean[] attendance = cur.getValue();
            GHUser user = cur.getKey();
            sb.append(user.getName() + ": ");
            for (int i = 0; i < attendance.length; i++) {
                if (attendance[i]) {
                    sum++;
                }
            }
            String percent = String.format("%.2f", (double)(sum*100)/TOTAL_ISSUE);
            sb.append(percent).append("\n");
        }
        System.out.println(sb.toString());
    }
}
