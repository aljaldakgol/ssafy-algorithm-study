import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제: SWEA 1204 최빈수 구하기
 * 메모리: 25,216 KB
 * 실행 시간: 82 ms
 * 알고리즘: 카운팅 정렬
 */

public class SWEA_1204_최빈수_구하기 {

	final static int MAX_SCORE = 100 + 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int[] score = new int[MAX_SCORE];

			br.readLine();
			st = new StringTokenizer(br.readLine());

			while (st.hasMoreElements()) {
				score[Integer.parseInt(st.nextToken())]++;
			}

			int ans = 0;
			for (int i = 0; i < MAX_SCORE; i++) {
				if (score[i] >= score[ans]) {
					ans = i;
				}
			}

			System.out.println("#" + t + " " + ans);
		}
	}

}
