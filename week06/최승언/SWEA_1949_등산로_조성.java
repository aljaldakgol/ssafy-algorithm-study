import java.util.*;
import java.io.*;

public class SWEA_1949_등산로_조성 {

	static int T, N, K, ans;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int maxNum = 0;
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;

					maxNum = Math.max(maxNum, num);
				}
			}

			List<int[]> maxPos = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxNum) {
						maxPos.add(new int[] { i, j });
					}
				}
			}

			ans = 0;
			for (int[] pos : maxPos) {
				visited[pos[0]][pos[1]] = true;
				dfs(pos[0], pos[1], 1, false);
				visited[pos[0]][pos[1]] = false;
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

	static void dfs(int x, int y, int length, boolean usedCut) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		
		ans = Math.max(ans, length);

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
				continue;

			if (map[nx][ny] >= map[x][y]) {
				if (usedCut || (map[nx][ny] - K >= map[x][y]))
					continue;

				int temp = map[nx][ny];
				map[nx][ny] = map[x][y] - 1;

				visited[nx][ny] = true;

				dfs(nx, ny, length + 1, true);
				
				visited[nx][ny] = false;
				
				map[nx][ny] = temp;
			} else {
				visited[nx][ny] = true;
				
				dfs(nx, ny, length + 1, usedCut);
				
				visited[nx][ny] = false;
			}
		}
	}

}
