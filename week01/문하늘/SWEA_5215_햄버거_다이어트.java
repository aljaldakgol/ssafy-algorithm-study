/**
 * 방법
 * 1) 테스트 케이스 받기
 * 2) 재료 갯수, 제한 칼로리 받기
 * 3) 재료들 ingredient array에 2차원 배열로 선호도, 칼로리 받기 : 2열로 해서 1열은 맛점수, 2열은 칼로리
 * 4) 재료 조합을 위한 배열 (크기는 재료 갯수 * 재료 종류 수)에 재료 인덱스들로 2차원 배열 만들기 각 행에 같은 햄버거) 단, 칼로리 넘지 않는 조합으로 
 * 5) 2차원 배열 행별로 순회하면서 재료들의 맛 점수 중 고득점 조합 출력하기 - 햄버거의 맛 점수의 합을 출력한다.
 */

package algorithm_study_week01;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class SWEA_5215_햄버거_다이어트 {
	static Scanner sc = new Scanner(System.in);
	
	// 재료 종류별 맛, 칼로리 배열
	public static int[][] GetIngredient(int numIngt){
		
		int [][] ingredientArr = new int[numIngt][2];
		
		for(int i = 0; i<numIngt; i++) {
			for(int j = 0; j<2; j++) {
				ingredientArr[i][j] = sc.nextInt();
			}
		}
		return ingredientArr;
		
	}
	
	// 재료 고르기 - 칼로리 제한
	public static int [][] GetCombi (int [][] ingredientArr, int numIngt) {
		int totalSubset = 1<<numIngt;
		int[][] combiArr = new int[totalSubset][];
		// 몇개의 재료를 입력받는 지 모르겠어서 어려움
		for(int i = 0; i<totalSubset; i++) {
			int count = Integer.bitCount(i);
			combiArr[i]=new int[count];
		} //모르겠따아아아ㅏㅏㅏ
		return combiArr;
	}
	
	// 가장 맛 점수 놓은 거 고르기
	public static int BestCombi() {
		
	}
	//메인
	public static void main(String[] args) {
	
		
		int test_case = sc.nextInt();
		
		for (int tc = 1; tc<=test_case ;tc++) {
			
			// 재료 입력받기
			int numIngt = sc.nextInt();
			int limitCal = sc.nextInt();
			int [][] ingredientArr = new int[numIngt][2];
			ingredientArr=GetIngredient(numIngt);
			
			// 2) 여러 갯수의 조합의 재료 받는데 칼로리 제한 넘기 전까지 -> 인덱스로 무슨 제료인지 확인하기
			List<List<Integer>> combiArr = new ArrayList<>();
			
		}
}
}
