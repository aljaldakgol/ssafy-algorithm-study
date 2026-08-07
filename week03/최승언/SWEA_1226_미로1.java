import java.util.*;
import java.io.*;

/*
 * 문제: SWEA 1226 미로1
 * 메모리: 25,984 kb
 * 실행 시간: 82 ms
 * 알고리즘: BFS
 */

public class SWEA_1226_미로1 {

	static int N = 16;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; t++) {
			br.readLine();

			int[] sPos = new int[2], ePos = new int[2];
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				char[] nums = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					int num = nums[j] - '0';
					map[i][j] = nums[j] - '0';

					if (num == 2) {
						sPos[0] = i;
						sPos[1] = j;
					}

					if (num == 3) {
						ePos[0] = i;
						ePos[1] = j;
					}
				}
			}

			sb.append("#").append(t).append(" ");
			sb.append(bfs(sPos, ePos)).append("\n");

		}

		System.out.println(sb);
	}

	static int bfs(int[] sPos, int[] ePos) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		Queue<int[]> que = new LinkedList<>();
		que.add(sPos);
		map[sPos[0]][sPos[1]] = 1;

		while (!que.isEmpty()) {
			int[] cPos = que.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cPos[0] + dx[d];
				int ny = cPos[1] + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1)
					continue;

				if (map[nx][ny] == 3)
					return 1;

				map[nx][ny] = 1;
				que.add(new int[] { nx, ny });
			}
		}

		return 0;
	}

}
