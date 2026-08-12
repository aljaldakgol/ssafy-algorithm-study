
package week02;
import java.io.*;
import java.util.*;

public class SWEA_2817_부분수열의_합 {
	
	static int N, K;
	static int[] arr;
	static int count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			
			st = new StringTokenizer (br.readLine());
			for(int i = 0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			count = 0;	// count 초기화 
			
			dfs(0, 0);	// 0th num 부터 시작한다. 현재까지의 합은 0이다.
			
			System.out.println("#" + tc + " "+ count);
			
		}
		
	}
	
	//dfs
	// idx : 지금 판단할 수의 인덱스 
	// sum : 지금까지 선택한 수의 합
	public static void dfs(int idx, int sum) {
		// 1) [가지치기 / 성공조건] 합이 K가 된 경우
		if(sum==K) {
			count++;
			return;
		}
		// 2) [가지치기 / 실패조건] 합이 K를 넘어가거나 N개의 수를 다 본 경우
		if(sum > K || idx ==N) {
			return;
		}
		// 3) 재귀호출
		// 3-1) 현재 숫자를 선택하는 경우 : sum에 더해주기
		dfs(idx + 1, sum+arr[idx]);
		
		// 3-2) 현재 숫자를 선택하지 않는 경우 : sum 유지
		dfs(idx + 1, sum);
	}
}
