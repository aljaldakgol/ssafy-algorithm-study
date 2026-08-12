import java.io.*;
import java.util.*;

/*
 * 문제: SWEA 1225 암호생성기
 * 메모리: 27,264 kb
 * 실행 시간: 85 ms
 * 알고리즘: 자료구조 (큐)
 */

public class SWEA_1225_암호생성기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());

			Queue<Integer> que = new ArrayDeque<>();
			for (int i = 0; i < 8; i++) {
				que.add(Integer.parseInt(st.nextToken()));
			}

			int minus = 0;
			while (true) {
				int num = que.poll();

				num -= (minus++ % 5) + 1;

				if (num <= 0) {
					num = 0;
					que.add(num);
					break;
				}

				que.add(num);
			}

			sb.append("#").append(t).append(" ");
			for (Integer num : que) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

}
