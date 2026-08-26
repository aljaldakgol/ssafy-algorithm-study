/**
 * 
 */
package week04;
import java.util.*;
import java.io.*;

public class SWEA_1979_어디에_단어가_들어갈_수_있을까 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= testCase; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());

			int puzzleLen = Integer.parseInt(st.nextToken());
			
			int wordLen=Integer.parseInt(st.nextToken());
			
			int [][] puzzle = new int[puzzleLen][puzzleLen];
			
			for (int i = 0 ; i<puzzleLen; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j<puzzleLen; j++) {
					puzzle[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			
			int countCase = 0;	// 조건에 맞는 경우 카운트하기
			int countOne=0;

			// 1) 행순회  puzzle[k][l]
			for(int k = 0; k<puzzleLen; k++) {
				countOne = 0;
				for (int l = 0; l<puzzleLen; l++) {
					if(puzzle[k][l]==1) {countOne++;}
					else {countOne=0;}

					if(countOne==wordLen&&(l==(puzzleLen-1)||puzzle[k][l+1]==0)) {
						countCase++;
					}
					
				}
			}
			// 2) 열 순회 puzzle[n][m]
			for(int m = 0; m<puzzleLen; m++) {
				countOne = 0;
				for (int n = 0; n<puzzleLen; n++) {
					if(puzzle[n][m] ==1) {countOne++;}
					else {countOne=0;}
					
					if(countOne==wordLen&&(n==(puzzleLen-1)||puzzle[n+1][m]==0)) {
						countCase++;
					}
					
				}
			}
			
			// 3) 결과 출력
			System.out.println("#"+tc+ " "+countCase);
		}
	}

}
