import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 문제: SWEA 1234 비밀번호
 * 메모리: 24,960 kb
 * 실행 시간: 76 ms
 * 알고리즘: 자료구조 (스택)
 */

public class SWEA_1234_비밀번호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			char[] cNum = st.nextToken().toCharArray();

			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < N; i++) {
				if (stack.isEmpty()) {
					stack.add(cNum[i]);
				} else if (stack.peek() == cNum[i]) {
					stack.pop();
				} else {
					stack.add(cNum[i]);
				}
			}

			sb.append("#").append(t).append(" ");
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
