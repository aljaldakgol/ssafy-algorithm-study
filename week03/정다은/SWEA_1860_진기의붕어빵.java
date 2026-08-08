import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            List<Integer> customers = new ArrayList<>();
            
            // 1. 손님 입력 받기
            for (int i=0;i<N;i++) {
            	customers.add(sc.nextInt());	
            }
            
            // 2. 정렬
            Collections.sort(customers);
            
            // 3. 가장 큰 숫자 반환
            int max = customers.get(N-1);
            
            // 4. 가장 큰 숫자까지 반복하면서 붕어빵 넣고 팔고 손님 추가 및 반복.
            int idx = 0;
            int boong = 0;
            boolean b = true;
            int cboong = 0;
            
            // 시간 초별로 반복하며 해당 초에 손님 수와 붕어빵 수를 비교하여 판별
            for (int i=0;i<=max;i++) {
                if ((i!=0) && (i %M==0)) {
                	boong += K;
                }
                
                if (idx < N && customers.get(idx) == i) {
                    cboong = 1;
                	while (true) {
                        if ((idx < N-1) && (customers.get(idx) == customers.get(idx+1))) {
                        	cboong++;
                        	idx++;
                        } else {
                        	break;
                        }
                	}
                    if (boong < cboong) {
                    	b = false;
                        break;
                    } else {
                    	boong -= cboong;
                    }
                    idx++;
                }
            }
            if (b==false) {
            	System.out.println("#"+test_case+" Impossible");
            } else {
            	System.out.println("#"+test_case+" Possible");
            }
		}
	}
}