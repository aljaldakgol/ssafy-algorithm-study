package week02.곽정민;

/*
 * 문제: SWEA 1234 비밀번호
 * 알고리즘: 스택
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_1234_비밀번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = 10; // 10개의 테스트 케이스가 고정으로 주어짐

        for (int tc = 1; tc <= test_case; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            String number = st.nextToken();

            Stack<Character> stack = new Stack<>();
            

            for (int i = 0; i < n; i++) {
                char c = number.charAt(i);
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            char[] sb = new char[stack.size()];
            for (int i = 0; i<stack.size(); ++i) {
                sb[i] = stack.elementAt(i);
            }

            System.out.println("#" + tc + " " + sb);
        }
    }
}
