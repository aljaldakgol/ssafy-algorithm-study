package week02.곽정민;

/*
 * 문제: SWEA 2817 부분 수열의 합
 * 알고리즘: 완전탐색(DFS 또는 비트마스크)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2817_부분_수열의_합 {

   static int count;

    static void dfs(int index, int total, int[] arr, int limitsum) {
        // base case: 모든 원소를 고려한 상태
        if (index == arr.length) {
            if (total == limitsum) {
                count++;
            }
            return;
        }

        dfs(index + 1, total + arr[index],arr,limitsum); // arr[index]를 선택하는 경우
        dfs(index + 1, total,arr,limitsum);              // arr[index]를 선택하지 않는 경우
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine().trim());
         int[] arr;
         int limitsum;
         
        for (int tc = 1; tc <= test_case; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            limitsum = Integer.parseInt(st.nextToken());

            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            count = 0;
            dfs(0, 0,arr,limitsum);

            System.out.println("#" + tc + " " + count);
        }
    }
}
