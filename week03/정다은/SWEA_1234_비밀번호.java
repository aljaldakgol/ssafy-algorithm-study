import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

class Solution {
	public static void main(String args[]) throws Exception {   
        Scanner sc = new Scanner(System.in);
		for(int test_case = 1; test_case <= 10; test_case++) {
            int N = sc.nextInt();
            List<Integer> nums = new ArrayList<>();
            String num = sc.next();
            // 리스트 생성
            for (int i=0;i<N;i++) {
                // 이 부분 참고
            	nums.add(num.charAt(i) - '0');
            }
			
            // 조회
            while (true) {
                if (nums.size()==0) {
                	break;
                }
                int cnt = 0;
            	for (int i=0;i<nums.size()-1;i++) {
                	if (nums.get(i).equals(nums.get(i+1))) {
                    	nums.remove(i);
                        nums.remove(i);
                        cnt++;
                        break;
                    }
                }
                if (cnt == 0) {
                    	break;
                }
            }
            System.out.print("#"+test_case+" ");
            for (int i=0;i<nums.size();i++) {
            	System.out.print(nums.get(i));
            }
            System.out.println();
		}
	}
}