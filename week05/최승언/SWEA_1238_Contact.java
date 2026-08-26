import java.util.*;
import java.io.*;

/*
 * 문제: SWEA 1238 Contact
 * 메모리: 25,600 kb
 * 실행 시간: 79 ms
 * 알고리즘: BFS
 * 
 * 아래 방법으로하면 굳이 Depth별로 나누지 않아도 됨
 * int size = que.size();
 *
 * for (int s = 0; s < size; s++) {
 * 		int curr = que.poll();
 * }
 */

public class SWEA_1238_Contact {

	static int T = 10, M = 101, N, S, ansDepth;
	static boolean[][] graph;
	static int[] ansByDepth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			graph = new boolean[M][M];
			for (int i = 0; i < N / 2; i++) {
				graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}

			ansDepth = 0;
			ansByDepth = new int[M];
			bfs();

			sb.append("#").append(t).append(" ").append(ansByDepth[ansDepth]).append("\n");
		}

		System.out.println(sb);

	}

	static void bfs() {
		boolean[] visited = new boolean[101];
		Queue<int[]> que = new ArrayDeque<>();

		que.add(new int[] { S, 0 });
		visited[S] = true;
		ansByDepth[0] = S;

		while (!que.isEmpty()) {
			int[] curr = que.poll();

			for (int i = 1; i < M; i++) {
				if (!graph[curr[0]][i] || visited[i])
					continue;

				int depth = curr[1] + 1;
				que.add(new int[] { i, depth });
				visited[i] = true;
				ansDepth = depth;
				ansByDepth[depth] = Math.max(ansByDepth[depth], i);
			}
		}
	}

}
