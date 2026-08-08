package week02.곽정민;

/*
 * 문제: SWEA 1225 암호생성기
 * 알고리즘: 큐 시뮬레이션
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1225_암호생성기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int test_case = 10; // 10개의 테스트 케이스가 고정으로 주어짐

        for (int tc = 1; tc <= test_case; tc++) {
             br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < 8; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }

            int[] decrease = { 1, 2, 3, 4, 5 };
            int cycle = 0;

            while (true) {
                int front = queue.poll();
                front -= decrease[cycle];
                cycle = (cycle + 1) % 5;

                if (front <= 0) {
                    queue.add(0); // 0으로 유지
                    break; // 프로그램 종료
                }
                queue.add(front);
            }

            StringBuilder sb = new StringBuilder();
             for (int num : queue) {
                sb.append(num + " ");
            }
            System.out.println("#" + tc + " "+sb.toString());
           
         
        }
    }
}
