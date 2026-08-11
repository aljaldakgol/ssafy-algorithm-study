/**
 * 개선 포인트 1) 큐 자료구조 사용하기
 * - 나의 풀이 : 배열 이동을 for 문으로 해서 배열 복사 연산을 하고 있다. 
 * - 선입선출 특성을 갖는 Queue<Integer> (linked list or ArrayDeque)를 사용하자
 * 
 * 개선 포인트 2) scanner 대신 bufferedReader 사용하기
 * - BufferedReader와 StringTokenizer로 입력 받고 StringBuilder로 출력을 모아서 한 번에 출력하자
 * 
 * 개선 포인트 3) 배열 이동 및 0 이하 처리 로직단순화 하기 
 * - 변수 temp, ttemp 등 여러 임시 변수를 썼는데 큐를 쓰면 된다...!!
 * 
 * 
 * 결론 : 아이디어는 동일한데 queue라는 자료구조를 이용하는 방법으로 해결하도록 하였고 쓸데 없는 변수들을 너무 많이 생성한 게 복잡하게 함
 */
package week03;

//import java.util.Scanner;
import java.io.*;
import java.util.*;


public class SWEA_1225_암호생성기 {

	public static void main(String[] args) throws IOException {

		/**
		 * Scanner sc = new Scanner(System.in);
		 * BufferedReader를 사용해야 한다. 
		 */
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//10개의 테스트케이스 
		for (int tcCount = 0; tcCount<10; tcCount++) {
			/**
			 * 1) 테스트케이스 값 입력받기
			 * Sting.trim()을 사용하면 입력 데이터에 포함될 수 있는 의도치 않은 공백이나 엔터 오류를 방지할 수 있다. 
			 * Integer.parseInt() 는 숫자 형태의 문자열만 변환 가능 -> trim으로 공백 제거 
			 */
			String tcLine = br.readLine();
			if(tcLine==null) break; // 예외 방지!
			int tc = Integer.parseInt(tcLine.trim());
			
			/**
			 *  2) 8개의 숫자 입력 받기
			 *  큐를 사용한다. 
			 *  int [] numArr = new int[8];
			 *  for (int j = 0; j<8; j++) {numArr[j]=sc.nextInt();}
			 */
			Queue<Integer> queue = new ArrayDeque<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0 ; i<8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			
			/**
			 * 3) 1사이클 시작하기 
			 * result 말고 좀 더 직관적인 isFinished로 변수 이름 변경
			 */
			boolean isFinished = false;
			
			while (!isFinished) {
				for (int r = 1; r<=5; r++) {

					// 맨 앞의 숫자 꺼내서 감
					// numArr[0]-=r;
					int num = queue.poll() - r;
					
					// 0 이하 -> 0 고정 + 맨 뒤로 
//					int temp = numArr[0]; // 0 이하인지 체크용 
//					int ttemp = numArr[0]; // 배열 넘겨줄 용 
//					if(numArr[0]<0) {
//						ttemp=0;	
//					}
					if(num<=0) {
						queue.add(0);
						ifFinished = true; 
						break;
					}
					
					// 0보다 크면 감소된 값을 맨 뒤로 삽
					queue.add(num);
				}
			}
				
			
//					for(int t = 0; t<7; t++) {
//						numArr[t]=numArr[t+1];
//					}
//					numArr[7]= ttemp;
//
//					if(temp <=0) {
//						result = false;
//						break;
//					}
//				}
//			}
//			
			// 4) 답 생성 (StringBuilder)
			sb.append("#").append(tc).append(" ");
			for (int num : queue) {
				sb.append(num).append(" ");
				
			}
			sb.append("\n");
		}
			System.out.println(sb);
		
	}

}
