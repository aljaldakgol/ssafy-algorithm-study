package week03;

import java.util.Scanner;

public class SWEA_1225_암호생성기 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		//10개의 테스트케이스 존
		for (int i = 0; i<10; i++) {
			// 1) 테스트케이스 값 입력받기
			int tc = sc.nextInt();
			// 2) 8개의 숫자 입력 받기
			int [] numArr = new int[8];
			// 3) 1사이클 시작하기 
			for (int j = 0; j<8; j++) {
				numArr[j]=sc.nextInt();
			}
			
			boolean result = true;
			
			while (result) {
				for (int r = 1; r<=5; r++) {

					numArr[0]-=r;
					int temp = numArr[0]; // 0 이하인지 체크용 
					int ttemp = numArr[0]; // 배열 넘겨줄 용 
					if(numArr[0]<0) {
						ttemp=0;	
					}
					
					for(int t = 0; t<7; t++) {
						numArr[t]=numArr[t+1];
					}
					numArr[7]= ttemp;

					if(temp <=0) {
						result = false;
						break;
					}
				}
			}
			
			// 4) 답 출력하
			System.out.print("#"+tc+" ");
			for (int k = 0; k<8;k++) {
				System.out.print(numArr[k]+" ");
			}
			System.out.printf("\n");
		}
		
	}

}
