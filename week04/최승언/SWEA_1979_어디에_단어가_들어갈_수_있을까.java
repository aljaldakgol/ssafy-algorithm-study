import java.util.*;
import java.io.*;

/*
 * 문제: SWEA 7465 어디에 단어가 들어갈 수 있을까
 * 메모리: 25,344 kb
 * 실행 시간: 72 ms
 * 알고리즘: 구현
 * 
 * 문제를 너무 어렵게 생각함
 * 그냥 행, 열 기준으로 몇 칸이 비었는지 확인하면 끝남
 */

public class SWEA_1979_어디에_단어가_들어갈_수_있을까 {

	static int T, N, K;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					if (st.nextToken().equals("1"))
						map[i][j] = true;
				}
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j])
						ans += checkBlank(i, j);
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);
	}

	static int checkBlank(int x, int y) {
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		int ans = 0;
		int nx = x + dx[0];
		int ny = y + dy[0];
		int px = x + dx[1];
		int py = y + dy[1];

		if (nx >= 0 && nx < N && ny >= 0) {
			if (map[nx][ny] && (px < 0 || !map[px][py])) {
				int cnt = 1;

				while (map[nx][ny]) {
					cnt++;

					nx += dx[0];
					ny += dy[0];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						break;
				}

				if (cnt == K) {
					ans++;
				}
			}
		}

		nx = x + dx[2];
		ny = y + dy[2];
		px = x + dx[3];
		py = y + dy[3];

		if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
			if (map[nx][ny] && (py < 0 || !map[px][py])) {
				int cnt = 1;

				while (map[nx][ny]) {
					cnt++;

					nx += dx[2];
					ny += dy[2];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						break;
				}

				if (cnt == K) {
					ans++;
				}
			}
		}

		return ans;
	}

}
