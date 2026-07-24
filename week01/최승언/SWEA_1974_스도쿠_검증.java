import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 문제: SWEA 1974 스도쿠 검증
 * 메모리: 25,344 KB
 * 실행 시간: 83 ms
 * 알고리즘: 구현
 */

public class SWEA_1974_스도쿠_검증 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int[][] board = new int[9][9];
			for (int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = 1;
			for (int i = 0; i < 9; i++) {
				Set<Integer> checkSet = new HashSet<>();
				for (int j = 0; j < 9; j++) {
					checkSet.add(board[i][j]);
				}
				if (checkSet.size() != 9) {
					ans = 0;
					break;
				}
			}

			if (ans != 0) {
				for (int i = 0; i < 9; i++) {
					Set<Integer> checkSet = new HashSet<>();
					for (int j = 0; j < 9; j++) {
						checkSet.add(board[j][i]);
					}
					if (checkSet.size() != 9) {
						ans = 0;
						break;
					}
				}
			}

			if (ans != 0) {
				outer: for (int i = 0; i < 9; i = i + 3) {
					for (int j = 0; j < 9; j = j + 3) {
						Set<Integer> checkSet = new HashSet<>();
						for (int x = 0; x < 3; x++) {
							for (int y = 0; y < 3; y++) {
								checkSet.add(board[i + x][j + y]);
							}
						}
						if (checkSet.size() != 9) {
							ans = 0;
							break outer;
						}
					}
				}
			}

			System.out.println("#" + t + " " + ans);

		}

	}

}
