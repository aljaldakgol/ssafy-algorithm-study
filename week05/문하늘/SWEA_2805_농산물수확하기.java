package week05;
import java.util.*;
import java.io.*;

public class SWEA_2805_농산물수확하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= testCase ; tc++) {
			int farmSize = Integer.parseInt(br.readLine().trim());
			int profit = 0;
			
			// i 는 행
			for(int i = 0; i<farmSize; i++) {
				// 행 단위로 읽기
				String product = br.readLine();
				
				// 0~중간행까지는 
				if (i>=0 && i<=farmSize/2) {
					for(int range=(farmSize/2-i); range<=(farmSize/2+i); range++){
						profit+=product.charAt(range)-'0';
					}
				}
				
				// 중간행+1~마지막행
				else {
					for(int range = (i-farmSize/2); range<(farmSize/2+farmSize-i); range++) {
						profit+=product.charAt(range)-'0';
					}
				}
			}
			
			System.out.println("#"+tc+ " " + profit);
			
		}
	}
}
 
/**
 * import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        String longNumStr = st.nextToken();

        // 1. 문자열 길이에 맞게 int 배열 생성
        int[] intArr = new int[longNumStr.length()];

        // 2. 한 자릿수씩 숫자로 변환해서 배열에 저장
        for (int i = 0; i < longNumStr.length(); i++) {
            // '0'을 빼주면 문자가 실제 정수로 변환됩니다 (예: '1' - '0' = 1)
            intArr[i] = longNumStr.charAt(i) - '0';
        }

        // 출력 확인
        System.out.println(Arrays.toString(intArr));
        // [1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3]
    }
}
 */
