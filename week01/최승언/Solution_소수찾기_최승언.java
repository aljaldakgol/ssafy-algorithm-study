public class Solution_소수찾기_최승언 {
	public int solution(int n) {
		int N = n + 1;
		boolean[] num = new boolean[N];

		num[0] = true;
		num[1] = true;

		int cNum = 2;
		while (cNum * cNum <= n) {
			for (int i = 2; cNum * i < N; i++) {
				num[i * cNum] = true;
			}

			for (int i = cNum + 1; i < N; i++) {
				if (!num[i]) {
					cNum = i;
					break;
				}
			}
		}

		int answer = 0;
		for (int i = 1; i < N; i++) {
			if (!num[i])
				answer++;
		}

		return answer;
	}
}
