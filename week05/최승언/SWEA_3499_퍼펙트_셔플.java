import java.util.*;
import java.io.*;

/*
 * 문제: SWEA 3499 퍼펙트 셔플
 * 메모리: 29,312 kb
 * 실행 시간: 98 ms
 * 알고리즘: 큐
 */

public class SWEA_3499_퍼펙트_셔플 {
	
	static int T, N;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			Queue<String> card1 = new ArrayDeque<>();
			Queue<String> card2 = new ArrayDeque<>();
			int size = 0;
			if (N % 2 == 0) {
				size = N / 2;
			} else {
				size = N / 2 + 1;
			}
			for (int n = 1; n <= N; n++) {
				if (n <= size) {
					card1.add(st.nextToken());
				} else {
					card2.add(st.nextToken());
				}
			}

			sb.append("#").append(t).append(" ");
			for (int n = 0; n < N; n++) {
				if (n % 2 == 0) {
					sb.append(card1.poll()).append(" ");
				} else {
					sb.append(card2.poll()).append(" ");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
