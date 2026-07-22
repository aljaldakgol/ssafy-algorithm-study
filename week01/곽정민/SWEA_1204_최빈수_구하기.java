/*
 * 문제: SWEA 1204 [S/W 문제해결 기본] 1일차 - 최빈수 구하기
 * 알고리즘: 빈도 배열(카운팅)
 */

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class SWEA_1204_최빈수_구하기 {

    public static void main(String[] args) throws IOException {

       Scanner sc = new Scanner(System.in);

        // 전체 테스트 케이스 개수
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int casenum = sc.nextInt();

            // score_count[n] = 값 n이 등장한 횟수 (0~100 범위이므로 크기 101)
            int [] score_count= new int[101];

            // 1000개의 수를 입력받으며 값별 등장 횟수를 누적
            for(int i = 0; i<1000; ++i){
                int score = sc.nextInt();
                score_count[score]++;
            }

            // 등장 횟수가 가장 큰 값(최빈값)을 탐색
            // 등장 횟수가 같으면(>=) 더 큰 값으로 갱신되어, 최빈값이 여러 개일 때 최댓값이 선택됨
			int max = 0;
            for(int i = 0; i<score_count.length; ++i){
                if (score_count[i] >= score_count[max]) {
                    max = i;
                }
            }
			System.out.println("#" + casenum + " " + max);
		}

    }
}
