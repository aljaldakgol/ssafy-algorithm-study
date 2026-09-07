import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_14510_나무_높이 {

	static int[] tree;
	static int maxTree, N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			tree = new int[N];

			maxTree = 0;
			for (int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				maxTree = Math.max(maxTree, tree[i]);
			}
			
			int one = 0, two = 0;
			
			for(int i = 0; i < N; i++) {
				int grow = maxTree - tree[i];
				
				one += grow % 2;
				two += grow / 2;
			}
			
			while(one + 1 < two) {
				two--;
				one += 2;
			}
			
			ans = Math.max(one*2 - 1, two * 2);
			
			System.out.println("#" + t + " " + ans);
		}
	}

}
