import java.util.Scanner;

class Solution {

    // 재료 개수
    static int N;

    // 제한 칼로리
    static int L;

    // 맛 점수 배열
    static int[] taste;

    // 칼로리 배열
    static int[] calorie;

    // 최대 맛 점수
    static int answer;

    //--------------------------------------------------
    // idx   : 현재 몇 번째 재료를 보고 있는가
    // score : 지금까지의 맛 점수
    // cal   : 지금까지의 칼로리
    //--------------------------------------------------
    static void dfs(int idx, int score, int cal) {

        // 칼로리 초과하면 더 볼 필요 없음
        if (cal > L) {
            return;
        }

        // 모든 재료를 다 확인했다면
        if (idx == N) {

            // 최대 점수 갱신
            if (score > answer) {
                answer = score;
            }

            return;
        }

        // -------------------------------
        // 현재 재료를 선택하는 경우
        // -------------------------------
        dfs(
                idx + 1,
                score + taste[idx],
                cal + calorie[idx]
        );

        // -------------------------------
        // 현재 재료를 선택하지 않는 경우
        // -------------------------------
        dfs(
                idx + 1,
                score,
                cal
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            // 재료 개수와 제한 칼로리 입력
            N = sc.nextInt();
            L = sc.nextInt();

            taste = new int[N];
            calorie = new int[N];

            // 재료 정보 입력
            for (int i = 0; i < N; i++) {
                taste[i] = sc.nextInt();
                calorie[i] = sc.nextInt();
            }

            // 정답 초기화
            answer = 0;

            // DFS 시작
            dfs(0, 0, 0);

            // 출력
            System.out.println("#" + test_case + " " + answer);
        }

        sc.close();
    }
}