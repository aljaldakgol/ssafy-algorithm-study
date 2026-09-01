import java.util.*;
import java.io.*;

public class SWEA_2117_홈_방범_서비스 {

	static int T, N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int hNum = 0;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());

					map[i][j] = num;

					if (num == 1)
						hNum++;
				}
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					ans = Math.max(ans, search(i, j, hNum));
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

	static int search(int x, int y, int hNum) {
		int ans = 0;

		for (int m = 1; m * m + (m - 1) * (m - 1) <= M * hNum; m++) {
			int cost = m * m + (m - 1) * (m - 1);
			int move = 0, flag = 1;
			int cnt = 0;

			for (int i = x - (m - 1); i <= x + (m - 1); i++) {
				for (int j = y - move; j <= y + move; j++) {
					if (i < 0 || i >= N || j < 0 || j >= N)
						continue;

					if (map[i][j] == 1) {
						cnt++;
					}
				}

				if (move == m - 1)
					flag *= -1;

				move += flag;
			}

			if (cnt * M >= cost) {
				ans = Math.max(ans, cnt);
			}

			
		}

		return ans;
	}

}
