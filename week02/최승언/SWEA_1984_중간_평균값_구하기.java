import java.io.*;
import java.util.*;

/*
 * 문제: SWEA 1984 중간 평균값 구하기
 * 메모리: 25,088 kb
 * 실행 시간: 73 ms
 * 알고리즘: 구현
 */

public class SWEA_1984_중간_평균값_구하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			double sum = 0;
			int maxNum = 0, minNum = 10001;
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreElements()) {
				int num = Integer.parseInt(st.nextToken());

				sum += num;

				maxNum = Math.max(maxNum, num);
				minNum = Math.min(minNum, num);
			}

			sum -= maxNum;
			sum -= minNum;

			sb.append(Math.round(sum / 8)).append("\n");

		}
		
		System.out.println(sb);

	}

}
