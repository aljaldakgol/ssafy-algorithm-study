/*
 * 문제: Programmers 12921 소수 찾기
 * 알고리즘: 에라토스테네스의 체
 */

public class Solution_소수찾기_곽정민 {
	public static int solution(int n) {

		boolean[] check = new boolean[n + 1];
		check[0] = check[1] = true;
		int answer = 0;
		for (int i = 2; i * i <= n; i++) {
			if (check[i])
				continue;
			for (int j = i + i; j <= n; j += i)
				check[j] = true;
		}

		for (int i = 2; i <= n; i++)
			if (!check[i]) {
				answer++;
			}

		return answer;
	}
}
