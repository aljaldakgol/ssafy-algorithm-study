import java.io.*;

/*
 * 문제: SWEA 2805 농작물 수확하기
 * 메모리: 26,880 kb
 * 실행 시간: 85 ms
 * 알고리즘: 구현, 수학
 * 
 * 맨하튼 거리
 * |x-i| + |y-j| < K
 */

public class SWEA_2805_농작물_수확하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			int[][] farm = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] nums = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					farm[i][j] = nums[j] - '0';
				}
			}

			int ans = 0;
			int flag = 1;
			int mid = N / 2, side = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < side; j++) {

					ans += farm[i][mid + j];
					if (j != 0) {
						ans += farm[i][mid - j];
					}

				}

				if (mid + side >= N)
					flag *= -1;

				side += flag;
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
