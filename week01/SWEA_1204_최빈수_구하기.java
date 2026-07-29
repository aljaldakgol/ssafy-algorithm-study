/*
 * 문제: SWEA_1204_최빈수_구하기
 * 메모리: 28,032 kb
 * 실행 시간: 107 ms
 * 알고리즘: 배열 정렬
 * 
 * 배운 점 :
 * 1) scanner로 입력 받는 것을 연습했었는데 시간 복잡도로 인해 오류가 났다.
 * 그래서 시간 복잡도를 줄이면서 입력 받는 방법을 찾아서 이를 적용했다.
 * 
 * 2)예외 처리 하는 것을 연습하지 않았는데 이를 적용하였다. 
 * 
 * 3) 성적입력 받는 배열(1000개의 인덱스)와 점수의 빈도수를 저장하는 배열(101개의 인덱스)를 따로 만들어서 입력 받고 또 배열을 방문해가면서 빈도 수를 파악했다
 * 그랬더니 너무 복잡했다
 * 입력 받은 점수가 중요한 것이 아니라 점수의 빈도가 중요한 것이라는 것을 알게 되었고
 * 입력 받는 동시에 점수의 빈도 수를 카운트함으로 인해 배열을 인덱스 101짜리 하나로 줄였다.
 * 
 * 
 * 시간 복잡도를 줄이는 것, 공간 복잡도를 줄이는 법을 더 공부해야 겠다.
 * 중요한 것과 중요히지 않은 것을 구분해야겠다
 */

package week01;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class SWEA_1204_최빈수_구하기 {

	static Scanner sc = new Scanner(System.in); // Scanner 객체 생성
	// 1. 입력 객체 생성
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int STUDENT_NUM = 1000;//학생수 1000
	
	
	// 성적 배열 입력 받아서 점슈 빈도 배열에 넣게
	private static int[] getScore(  ) throws IOException {
	
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int [] scoreFrq=new int[101]; 

		for (int i = 0; i < STUDENT_NUM; i++) {
			int score = Integer.parseInt(st.nextToken());
			scoreFrq[score]++;
		}
		
		return scoreFrq;
		
	}

	private static int mostMaxScore(int[] scoreFrq) {
		int maxMost = 0;
		int maxScore=0;
		for(int j = 0; j<101;j++){
			if(maxScore<=scoreFrq[j]) {
				maxMost=j;
				maxScore=scoreFrq[j];
			}
		}
		return maxMost ;
	}
	
	public static void main(String[] args)throws IOException{
		
		int numT = Integer.parseInt(br.readLine());	
		
		// 테스트 케이스 수 만큼 반복하기
		for (int idxT=0 ; idxT<numT; idxT++) {
			int tcNum = Integer.parseInt(br.readLine());
			
			// 성적 배열 입력 받아서 점수 빈도수 구하기
			int[] scoreFrq = new int[101];
			scoreFrq = getScore( );
			
			// 최빈수&&가장 큰 점수 알아내기
			int maxMost=0;
 
			maxMost=mostMaxScore(scoreFrq);
			
			// 출력하기
			System.out.printf("#%d %d\n",tcNum ,maxMost);
		}

	}
}