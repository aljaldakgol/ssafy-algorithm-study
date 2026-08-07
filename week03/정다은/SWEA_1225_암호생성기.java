import java.util.Scanner;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;

// 선입선출이라 큐 사용
class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= 10; test_case++) {
            int t = sc.nextInt();
			Queue<Integer> queue = new LinkedList<>();
            for (int i=0;i<8;i++) {
            	queue.add(sc.nextInt());
            }
            
            int num;
            boolean b = true;
            // 0이 나올 때까지 while
            while (b) {
            	// 한 사이클은 5번 반복
                for (int i=1;i<6;i++) {
                    num = queue.poll();
                    num -= i;
                    
                    if (num <= 0 ) {
                    	num = 0;
                    }
                	queue.add(num);
                    if (num == 0) {
                        b = false;
                    	break;
                    }
                }
            }
            
            System.out.print("#"+test_case+" ");
            for (int i=0;i<8;i++) {
            	System.out.print(queue.poll()+" ");
            }
            System.out.println();
		}
	}
}