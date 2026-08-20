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
