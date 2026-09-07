import java.io.*;
import java.util.*;

public class SWEA_1267_작업순서 {

	static class Node {
		int num;
		List<Integer> nextJob;

		public Node(int num) {
			this.num = num;
			nextJob = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
//			int E = Integer.parseInt(st.nextToken());

			Node[] nodes = new Node[V + 1];
			for (int v = 1; v <= V; v++) {
				nodes[v] = new Node(v);
			}
			
			int[] remainJob = new int[V + 1];
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreElements()) {
				int requiredJob = Integer.parseInt(st.nextToken());
				int job = Integer.parseInt(st.nextToken());
				
				nodes[requiredJob].nextJob.add(job);
				remainJob[job]++;
			}
			
			
			Queue<Integer> que = new LinkedList<>();
			
			for(int v = 1; v < V + 1; v++) {
				if(remainJob[v] == 0) {
					que.add(nodes[v].num);
				}
			}
			
			while(!que.isEmpty()) {
				int curr = que.poll();
				
				sb.append(curr).append(" ");
				
				for(int nJob : nodes[curr].nextJob) {
					remainJob[nJob]--;
					
					if(remainJob[nJob] == 0) {
						que.add(nJob);
					}
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

}
