import java.util.*;
import java.io.*;

public class SWEA_1251_하나로 {

	static int T, N;
	static double E;
	static int[][] island;
	static int[] root;

	static class Edge implements Comparable<Edge> {
		int from, to;
		double pay;

		public Edge(int from, int to, double pay) {
			this.from = from;
			this.to = to;
			this.pay = pay;
		}

		public int compareTo(Edge e) {
			return Double.compare(this.pay, e.pay);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			island = new int[N][2];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				island[n][0] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				island[n][1] = Integer.parseInt(st.nextToken());
			}

			E = Double.parseDouble(br.readLine());

			root = new int[N];
			List<Edge> edges = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double len = Math.pow(Math.abs(island[i][0] - island[j][0]), 2)
							+ Math.pow(Math.abs(island[i][1] - island[j][1]), 2);

					edges.add(new Edge(i, j, len * E));
				}
				root[i] = i;
			}

			Collections.sort(edges);

			double ans = 0;
			for (Edge e : edges) {
				if (find(e.from) == find(e.to))
					continue;

				union(e.from, e.to);
				ans += e.pay;
			}

			sb.append("#").append(t).append(" ").append(Math.round(ans)).append("\n");
		}

		System.out.println(sb);
	}

	static int find(int x) {
		if (root[x] == x) {
			return x;
		}

		return root[x] = find(root[x]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return;

		root[rootB] = rootA;
	}
}
