/*
 * 문제: Programmers 12977 소수 만들기
 * 알고리즘: 완전탐색(조합) + 소수 판별
 *
 * [풀이 도움 여부]
 * 전체 구현을 AI(Claude Code)에게 요청하여 작성
 *
 * [나의 생각 과정]
 * (직접 시도하지 않고 구현을 바로 요청함)
 *
 * [막힌 부분]
 * 없음
 *
 * [참고한 내용]
 * Claude Code에게 전체 구현을 요청했다.
 *
 * [최종적으로 이해한 해결 방법]
 * nums의 길이가 최대 50이라 서로 다른 세 수를 고르는 조합(C(50,3)=19600)을 3중 for문으로
 * 모두 순회해도 충분히 빠르다. 각 조합의 합을 구해 isPrime으로 소수인지 판별하고,
 * 소수인 경우만 answer를 늘려 최종 개수를 반환한다.
 */

public class Solution_소수만들기_곽정민 {

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int solution(int[] nums) {
        int answer = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (isPrime(nums[i] + nums[j] + nums[k])) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    // 3중 for문은 "정확히 3개 선택"에 고정되어 있어, 나중에 선택 개수(k)가 커지거나
    // 가변적으로 바뀌면 반복문을 추가로 중첩해야 한다. 백트래킹으로 짜두면
    // pickCount만 바꿔서 k개 선택으로 쉽게 확장할 수 있다.
    static final int PICK_COUNT = 3;

    public int solutionBacktracking(int[] nums) {
        return dfs(nums, 0, 0, 0);
    }

    // start: 다음으로 고려할 인덱스, count: 지금까지 고른 개수, sum: 지금까지 고른 값의 합
    static int dfs(int[] nums, int start, int count, int sum) {
        if (count == PICK_COUNT) {
            return isPrime(sum) ? 1 : 0;
        }

        int total = 0;
        for (int i = start; i < nums.length; i++) {
            total += dfs(nums, i + 1, count + 1, sum + nums[i]); // nums[i]를 고르고 다음 인덱스부터 재귀 (선택 후 자동으로 되돌아옴)
        }
        return total;
    }
}
