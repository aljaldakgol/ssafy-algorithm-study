import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제: SWEA 5215 햄버거 다이어트
 * 메모리: 26,624 KB
 * 실행 시간: 149 ms
 * 알고리즘: 백트래킹 완전 탐색
 */

public class SWEA_5215_햄버거_다이어트 {

	static int ans = 0;
	static int L, N;
	static int[] cal, score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			cal = new int[N];
			score = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				cal[i] = Integer.parseInt(st.nextToken());
			}

			ans = 0;
			
			dfs(0, 0, 0);
			
			System.out.println("#" + t + " " + ans);
		}

	}

	public static void dfs(int choice, int totalScore, int totalCal) {
		
		if(choice >= N)
			return;
		
		if(totalCal + cal[choice] <= L) {
			ans = Math.max(ans, totalScore + score[choice]);
			dfs(choice + 1, totalScore + score[choice], totalCal + cal[choice]);
		}
		
		dfs(choice + 1, totalScore, totalCal);
		
	}

}
