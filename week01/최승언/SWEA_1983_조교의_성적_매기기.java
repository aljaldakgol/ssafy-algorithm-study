import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 문제: SWEA 1983 조교의 성적 매기기
 * 메모리: 25,728 KB
 * 실행 시간: 76 ms
 * 알고리즘: 배열 정렬
 */

public class SWEA_1983_조교의_성적_매기기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			double kScore = 0;
			ArrayList<Double> scoreList = new ArrayList<>();
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());

				double score1 = Integer.parseInt(st.nextToken()) * 0.35;
				double score2 = Integer.parseInt(st.nextToken()) * 0.45;
				double score3 = Integer.parseInt(st.nextToken()) * 0.20;

				scoreList.add(score1 + score2 + score3);

				if (n + 1 == K) {
					kScore = score1 + score2 + score3;
				}
			}

			Collections.sort(scoreList);

			int alpaScore = 0, cnt = 0;
			String[] alpa = { "D0", "C-", "C0", "C+", "B-", "B0", "B+", "A-", "A0", "A+" };
			for (double s : scoreList) {
				cnt++;

				if (s == kScore) {
					System.out.println("#" + t + " " + alpa[alpaScore]);
					break;
				}

				if (cnt % (N / 10) == 0)
					alpaScore++;

			}
		}

	}

}
