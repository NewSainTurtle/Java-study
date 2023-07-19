import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

public class Problem01 {
    private static final String Token = "토큰 입력";
    public static void main(String[] args) throws IOException {
        //GitHub 토큰 연동
        GitHub gitHub = new GitHubBuilder().withOAuthToken(Token).build();

        //Repository 연결
        GHRepository ghRepository = gitHub.getRepository("NewSainTurtle/CS-study");

        StringBuilder sb = new StringBuilder();

        // 이슈 받아오기 - IssueState All, OPEN, CLOSED
        List<GHIssue> issues = ghRepository.getIssues(GHIssueState.ALL);
        Map<String,Integer> user = new HashMap<>();

        // 각 이슈 정보
        for(GHIssue ghIssue : issues) {
            Set<String> count = new HashSet<>();
            // 중복 아이디 제거
            for(GHIssueComment ghIssueComment : ghIssue.getComments()) {
                String id = ghIssueComment.getUser().getLogin();
                count.add(id);
            }
            // 아이디 카운트
            for(String id : count) {
                user.compute(id, (key,val)->val==null? 1: val+1);
            }
        }
        for(String id : user.keySet()) {
            double rate = (double)(user.get(id) * 100) / issues.size();
            sb.append("\"").append(id).append("\"님의 출석률 : ").append(String.format("%.2f",rate)+"%").append('\n');
        }
        System.out.println(sb);
    }
}
