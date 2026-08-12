import java.util.*;
import java.io.*;

/*
 * 문제: SWEA 7465 창용 마을 무리의 개수
 * 메모리: 28,544 kb
 * 실행 시간: 95 ms
 * 알고리즘: BFS
 */

public class SWEA_7465_창용_마을_무리의_개수 {

	static int T, N, M;
	static boolean[] visited;
	static Nei[] member;

	static class Nei {
		int num;
		ArrayList<Integer> know;

		public Nei(int num) {
			this.num = num;
			know = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			visited = new boolean[N + 1];
			member = new Nei[N + 1];
			
			for(int i = 1; i < N + 1; i++) {
				member[i] = new Nei(i);
			}
			
			for(int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int nei1 = Integer.parseInt(st.nextToken());
				int nei2 = Integer.parseInt(st.nextToken());
				// bfs로 풀기 때문에 양방향으로 만들기
				member[nei1].know.add(nei2);
				member[nei2].know.add(nei1);
			}
			
			int ans = 0;
			for(int i = 1; i < N + 1; i++) {
				ans += bfs(i);
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			
		}
		
		System.out.println(sb);
	}
	
	static int bfs(int num) {
		Queue<Integer> que = new LinkedList<>();
		
		if(visited[num])
			return 0;
		
		que.add(num);
		visited[num] = true;
		
		while(!que.isEmpty()) {
			int cNum = que.poll();
			
			for(Integer nei : member[cNum].know) {
				if(visited[nei])
					continue;
				
				visited[nei] = true;
				que.add(nei);
			}
		}
		
		return 1;
	}

}
