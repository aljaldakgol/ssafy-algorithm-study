/*
 * 에라토스테네스의 체를 통해 소수를 미리 찾아놓고
 * 백트래킹으로 3개의 수를 더하는 조합에 대한 소수 판정을 진행
 * 
 * 최대 실행 시간: 0.63ms
 * 최대 메모리: 81.6MB
 */

public class Solution_소수만들기_최승언 {

	static int ans, size;
	static boolean[] isPrime;

	public int solution(int[] nums) {

		size = nums.length;

		int maxSize = 1000 + 999 + 998 + 1;
		isPrime = new boolean[maxSize];

		isPrime[0] = true;
		isPrime[1] = true;

		int num = 2;
		while (num * num <= maxSize) {
			for (int i = 2; i * num < maxSize; i++) {
				if (isPrime[i * num])
					continue;

				isPrime[i * num] = true;

			}

			for (int i = num + 1; i < maxSize; i++) {
				if (!isPrime[i]) {
					num = i;
					break;
				}
			}
		}

		dfs(nums, 0, 0, 0);

		return ans;
	}

	public static void dfs(int[] nums, int idx, int sum, int cnt) {

		if (cnt == 3) {
			if (!isPrime[sum])
				ans++;
			return;
		}

		if (idx >= size)
			return;

		dfs(nums, idx + 1, sum + nums[idx], cnt + 1);

		dfs(nums, idx + 1, sum, cnt);

	}

}
