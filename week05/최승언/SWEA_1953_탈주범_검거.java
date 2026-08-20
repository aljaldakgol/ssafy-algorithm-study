import java.util.*;
import java.io.*;

/*
 * 문제: SWEA 1953 탈주범 검거
 * 메모리: 31,104 kb
 * 실행 시간: 146 ms
 * 알고리즘: BFS
 */

public class SWEA_1953_탈주범_검거 {

	static int T, N, M, ans;
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
			int nPos = Integer.parseInt(st.nextToken());
			int mPos = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;
			bfs(nPos, mPos, time);

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	static void bfs(int nPos, int mPos, int time) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		int[][] pipe = new int[8][];
		pipe[1] = new int[] { 0, 1, 2, 3 };
		pipe[2] = new int[] { 0, 1 };
		pipe[3] = new int[] { 2, 3 };
		pipe[4] = new int[] { 0, 3 };
		pipe[5] = new int[] { 1, 3 };
		pipe[6] = new int[] { 1, 2 };
		pipe[7] = new int[] { 0, 2 };

		int[][] conn = new int[4][];
		conn[0] = new int[] { 1, 2, 5, 6 };
		conn[1] = new int[] { 1, 2, 4, 7 };
		conn[2] = new int[] { 1, 3, 4, 5 };
		conn[3] = new int[] { 1, 3, 6, 7 };

		boolean[][] visited = new boolean[N][M];
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] { nPos, mPos, 1 });
		visited[nPos][mPos] = true;
		ans++;

		if (time == 1)
			return;

		while (!que.isEmpty()) {
			int[] curr = que.poll();

			for (int p : pipe[map[curr[0]][curr[1]]]) {
				int nx = curr[0] + dx[p];
				int ny = curr[1] + dy[p];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0 || visited[nx][ny])
					continue;

				boolean flag = false;
				for (int c : conn[p]) {
					if (c == map[nx][ny]) {
						flag = true;
						break;
					}
				}

				if (!flag)
					continue;

				visited[nx][ny] = true;
				ans++;

				if (curr[2] + 1 >= time)
					continue;

				que.add(new int[] { nx, ny, curr[2] + 1 });
			}
		}
	}

}
