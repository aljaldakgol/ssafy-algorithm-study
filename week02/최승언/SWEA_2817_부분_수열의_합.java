import java.io.*;
import java.util.*;

/*
 * 문제: SWEA 2817 부분 수열의 합
 * 메모리: 26,112 kb
 * 실행 시간: 142 ms
 * 알고리즘: 부분집합, 백트래킹
 */

public class SWEA_2817_부분_수열의_합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int[] nums = new int[N];
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			int ans = dfs(nums, N, K, 0, 0);

			sb.append(ans).append("\n");
		}

		System.out.println(sb);
	}

	public static int dfs(int[] nums, int n, int k, int sum, int idx) {
		int cnt = 0;

		if (sum == k)
			return 1;

		if (idx >= n)
			return 0;

		cnt += dfs(nums, n, k, sum + nums[idx], idx + 1);

		cnt += dfs(nums, n, k, sum, idx + 1);

		return cnt;
	}

}
