import java.util.*;
import java.io.*;

/*
 * 문제: SWEA 4014 활주로 건설
 * 메모리: 27,648 kb
 * 실행 시간: 131 ms
 * 알고리즘: 구현
 */

public class SWEA_4014_활주로_건설 {

	static int T, N, X;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = 0;
			outer1: for (int i = 0; i < N; i++) {
				boolean[] runway = new boolean[N];
				for (int j = 1; j < N; j++) {
					int num = map[i][j - 1];

					if (num - map[i][j] == -1) {
						int flatNum = map[i][j - 1];
						for (int x = 1; x <= X; x++) {
							if (j - x < 0 || flatNum != map[i][j - x] || runway[j - x])
								continue outer1;
						}

						for (int x = 1; x <= X; x++) {
							runway[j - x] = true;
						}
					} else if (num - map[i][j] == 1) {
						int flatNum = map[i][j];

						for (int x = 0; x < X; x++) {
							if (j + x >= N || flatNum != map[i][j + x] || runway[j + x])
								continue outer1;
						}

						for (int x = 0; x < X; x++) {
							runway[j + x] = true;
						}
					} else if ((int) Math.abs(num - map[i][j]) > 1) {
						continue outer1;
					}
				}
				ans++;
			}

			outer2: for (int i = 0; i < N; i++) {
				boolean[] runway = new boolean[N];
				for (int j = 1; j < N; j++) {
					int num = map[j - 1][i];

					if (num - map[j][i] == -1) {
						int flatNum = map[j - 1][i];
						for (int x = 1; x <= X; x++) {
							if (j - x < 0 || flatNum != map[j - x][i] || runway[j - x])
								continue outer2;
						}

						for (int x = 1; x <= X; x++) {
							runway[j - x] = true;
						}
					} else if (num - map[j][i] == 1) {
						int flatNum = map[j][i];

						for (int x = 0; x < X; x++) {
							if (j + x >= N || flatNum != map[j + x][i] || runway[j + x])
								continue outer2;
						}

						for (int x = 0; x < X; x++) {
							runway[j + x] = true;
						}
					} else if ((int) Math.abs(num - map[j][i]) > 1) {
						continue outer2;
					}
				}
				ans++;
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

}
