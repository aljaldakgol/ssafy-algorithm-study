package week01;

import java.util.Scanner;
//import java.io.FileInputStream;


public class SWEA_1974_스도쿠_검증 {
	public static void main(String args[]) throws Exception {
	
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int[][]matrix = new int[9][9];
			for (int i = 0; i<9; i++) {
				for(int j = 0; j<9; j++) {
					matrix[i][j]=sc.nextInt();
				}
			}
			
			int result = 1;
			
			
			// 1) row
			for (int i = 0; i<9; i++) {
				for (int j = 0 ; j<8; j++) {
					for(int k = 1; k<9-j; k++) {
						if(matrix[i][j]==matrix[i][j+k]) {
							result = 0;
						}
					}
				}
			}
			// 2) column
			for (int i = 0; i<9; i++) {
				for (int j = 0 ; j<8; j++) {
					for(int k = 1; k<9-j; k++) {
						if(matrix[j][i]==matrix[j+k][i]) {
							result= 0;
						}
					}
				}
			}
			// 3) small
			for (int i = 0; i < 9; i = i + 3) {
				for (int j = 0; j < 9; j = j + 3) {
					
					for (int k = 0; k < 3; k++) {
						for (int l = 0; l < 3; l++) {
							
							for (int m = 0; m < 3; m++) {
								for (int n = 0; n < 3; n++) {
									
									if (!((k == m) && (l == n)) && (matrix[i + k][j + l] == matrix[i + m][j + n])) {
										result = 0;
									}
									
								}
							}
							
						}
					}
					
				}
			}
				
			System.out.printf("#%d %d\n", test_case,result);
		}
	}
}