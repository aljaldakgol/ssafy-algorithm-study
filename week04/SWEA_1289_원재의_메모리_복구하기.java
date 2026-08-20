/**
 * 현재 코드는 "뒤쪽 비트들을 직접 하나씩 다 바꿔주는 시뮬레이션 O(N^2) 방식으로 동작
 * 1) 매번 뒤쪽 배열을 for문으로 덮어쓸 이유가 없다.
 * 2) 문자열을 굳이 int[]로 바꾸지 않아도 된다. String.charAt() 직접 활용 가능
 * 3) 자바는 기본적으로 배열을 0으로 초기화한다. 따라서 for문으로 초기화하지 않아도 된다. 
 * 
 * tip) break 대신 searchFin = true; 했을 때 오류가 났는데 이유
 * 배열 인덱스 초과 에러 : while문 조건 검사 시점 때문에! break은 즉시 while문을 탈출한다. 
 */
package week04;
import java.util.*;
import java.io.*;

public class SWEA_1289_원재의_메모리_복구하기 {

	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1 ; tc<= TestCase ; tc++) {
			// 메모리문자열을 받아온다. 
			String memoryConverted = br.readLine().trim();
			
			int memoryLen = memoryConverted.length();
			int[] memoryArr = new int [memoryLen];
			
			// 입력받은 메모리문자열을 배열로 바꿔준다. 
			for(int idx = 0; idx < memoryLen; idx++) {
				memoryArr[idx] = memoryConverted.charAt(idx) - '0';
			}
			
			// 시작 배열 선언 후 0으로 초기화
			int[] startArr = new int [memoryLen];
			
			for (int i = 0; i<memoryLen; i++) {
				startArr[i]=0;
			}
			
			boolean searchFin = false; 
			int moveIdx = 0;	// 이동하면서 인덱스 별로 비교하기 
			int count = 0;		// 찾았으면 + 1
			
			while (!searchFin) {
				
				if(startArr[moveIdx]!= memoryArr[moveIdx]) {
					
					count++;
					
					for (int j = moveIdx; j<memoryLen; j++) {
						startArr[j]=memoryArr[moveIdx];
					}
				}
				
				moveIdx++;
				
				if(moveIdx==memoryLen) {
					break;
				}
			}
			
			System.out.println("#"+ tc + " "+ count);
		}
		
		
	}

}
