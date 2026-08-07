import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

// 정리하자면, 정상 가격표와 할인 가격표가 뒤섞여 있는 상황, 이 상황에서 정상 가격표만 찾아내기.
class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {
            // 품목 개수
			int num = sc.nextInt();
            List<Long> list = new ArrayList<>();
            List<Long> answer = new ArrayList<>();
            
            // 값 넣기. 무조건 앞의 값이 할인 가격일수밖에 없음
            for (int i=0;i<num*2;i++) {
            	list.add(sc.nextLong());
            }
            
            // 탐색. 첫 번째 값을 빼서 discount에 넣고 queue를 돌면서 찾음
            long discount;
            for (int i=0;i<num;i++) {
            	discount = list.remove(0);
                for (int j=0;j<list.size();j++) {
                	if (discount * 100 == list.get(j) * 75) {
                    	list.remove(j);
                        break;
                    }
                }
                answer.add(discount);
            }
            System.out.print("#"+test_case+" ");
            for (int i=0;i<num;i++) {
            	System.out.print(answer.remove(0)+" ");
            }
            System.out.println();
		}
	}
}