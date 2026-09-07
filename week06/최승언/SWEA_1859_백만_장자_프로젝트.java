import java.util.*;
import java.io.*;

public class SWEA_1859_백만_장자_프로젝트 {

	static int T, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			int[] price = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			int max = 0;
			long ans = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (price[i] >= max) {
					max = price[i];
				} else {
					ans = ans + max - price[i];
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
