import java.io.*;
import java.util.*;

public class SWEA_1249_보급로 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			int N = Integer.parseInt(br.readLine());

			int[][] map = new int[N][N];
			int[][] visited = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = line[j] - '0';
					visited[i][j] = Integer.MAX_VALUE;
				}
			}

			bfs(map, visited, N);

			sb.append(visited[N - 1][N - 1]).append("\n");
		}
		
		System.out.println(sb);

	}

	static void bfs(int[][] map, int[][] visited, int N) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		Queue<int[]> que = new LinkedList<>();
		visited[0][0] = 0;
		que.add(new int[] { 0, 0 });

		while (!que.isEmpty()) {
			int[] curr = que.poll();
			int currTime = visited[curr[0]][curr[1]];

			for (int d = 0; d < 4; d++) {
				int nx = curr[0] + dx[d];
				int ny = curr[1] + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;

				if (visited[nx][ny] <= currTime + map[nx][ny])
					continue;

				visited[nx][ny] = currTime + map[nx][ny];
				que.add(new int[] { nx, ny });
			}
		}

	}

}
