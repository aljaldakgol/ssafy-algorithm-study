import java.util.*;
import java.io.*;

public class SWEA_3307_최장_증가_부분_수열 {

	static int T, N;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			nums = new int[N];
			int[] dp = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
				dp[i] = 1;
			}

			int ans = 0;
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < i; j++) {
					if (nums[j] < nums[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
						ans = Math.max(ans, dp[i]);
					}
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
