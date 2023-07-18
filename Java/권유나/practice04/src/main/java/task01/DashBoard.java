package task01;

import org.kohsuke.github.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/***
 * live-study 대시 보드를 만드는 코드를 작성하세요.
 *
 * 깃헙 이슈 댓글을 순회하며 댓글을 남긴 사용자를 체크 할 것.
 * 참여율을 계산하세요. 총 18회에 중에 몇 %를 참여했는지 소숫점 두자리가지 보여줄 것.
 * Github 자바 라이브러리를 사용하면 편리합니다.
 * 깃헙 API를 익명으로 호출하는데 제한이 있기 때문에 본인의 깃헙 프로젝트에 이슈를 만들고 테스트를 하시면 더 자주 테스트할 수 있습니다.
 ***/

public class DashBoard {

    final static GitHub github = GitHubConnect.getConnection();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String repository_name = "NewSainTurtle/CS-study";
    static GHRepository repository;

    public static void main(String[] args) throws IOException {
        repository = github.getRepository(repository_name);
        menu();
    }

    static void menu() throws IOException {
        while (true) {
            System.out.println("\n---------------Menu(숫자를 입력하세요)---------------");
            System.out.println("1. 참여율 확인하기\n2. 과제완료 여부 확인하기\n3. 종료");

            int order = inputNumber(1, 3);

            if (order == 1) {
                checkParticipationRate();
            } else if (order == 2) {
                checkPractice();
            } else {
                return;
            }
        }
    }

    static void checkParticipationRate() throws IOException {
        List<GHIssue> issues = repository.getIssues(GHIssueState.ALL);
        Map<String, Integer> map = new HashMap<>();
        for (GHIssue issue : issues) {
            Set<String> issue_users = new HashSet<>();
            for (GHIssueComment comment : issue.getComments()) {
                issue_users.add(comment.getUser().getLogin());
            }

            for (String user : issue_users) {
                if (!map.containsKey(user)) {
                    map.put(user, 1);
                } else {
                    map.replace(user, map.get(user) + 1);
                }
            }
        }

        int issue_size = issues.size();
        int totalCount = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("\n===================1. 참여율 확인하기===================\n");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey() + "\t" + String.format("%.2f", ((double) entry.getValue() / issue_size) * 100) + "%\n");
            totalCount += entry.getValue();
        }
        sb.append("과제 수: " + issue_size + "개, 평균 참여율: "+String.format("%.2f", (((double)totalCount/map.size()) / issue_size) * 100) + "%\n");
        System.out.println(sb);
    }

    static void checkPractice() throws IOException {
        System.out.println("\n=========2. 과제완료 여부 확인하기(숫자를 입력하세요)=========");
        System.out.println("------과제완료 여부를 알고 싶은 과제의 번호를 입력하세요-------");

        List<GHIssue> issues = repository.getIssues(GHIssueState.ALL);
        for (int i = issues.size() - 1; i >= 0; i--) {
            System.out.println((issues.size() - i) + ". " + issues.get(i).getTitle());
        }
        System.out.println((issues.size() + 1) + ". 뒤로가기");
        int order = inputNumber(1, issues.size() + 1);
        if (order == issues.size() + 1) {
            return;
        } else {
            System.out.println("\n------------------계정을 입력하세요-------------------");
            System.out.print(">> 입력: ");
            String login = br.readLine();

            for (GHIssueComment comment : issues.get(issues.size() - order).getComments()) {
                if (comment.getUser().getLogin().equals(login)) {
                    System.out.println("과제를 완료했습니다.");
                    return;
                }
            }
            System.out.println("과제를 미완료했습니다.");
        }
    }

    static int inputNumber(int start, int end) throws IOException {
        System.out.print(">> 입력: ");
        try {
            int order = Integer.parseInt(br.readLine());
            if (order >= start && order <= end) {
                return order;
            } else {
                throw new RuntimeException("Error: 메뉴에 없는 숫자를 입력했습니다.");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: 숫자 외 문자를 입력했습니다.");
        }
    }
}
