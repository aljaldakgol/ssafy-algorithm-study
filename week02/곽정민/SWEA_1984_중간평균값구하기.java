package week02.곽정민;

/*
 * 문제: SWEA 1984 중간평균값 구하기
 * 알고리즘: 정렬
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class SWEA_1984_중간평균값구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= test_case; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int n = 10; 
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);


          
            

            // TODO: 최댓값, 최솟값 제외한 합 구하기
            int sum = 0;
            for(int i = 1; i< arr.length - 1;++i){
                sum += arr[i];
            }
            double avg = Math.round((double)sum / ((double)arr.length-2));

            // TODO: 평균 계산 (형변환/반올림 방식 확인)

             System.out.println("#" + tc + " " + (int)avg);
        }
    }
}
