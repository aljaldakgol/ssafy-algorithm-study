import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


// 일단 Scanner로 푼 이유. 여기서 기본으로 제공해주는거기도 하고, BufferdList 써보려 했으나 import 해야하는게 너무 많아서 코테 때 다 못 외울 것 같아서 문제 상황을 마주하기 전까진 일단 Scanner 사용해서 풀어보겠음 
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            List<Integer> nums = new ArrayList<>();
            int min = 99999;
            int max = 0;
            float avg = 0;
            // 값을 받는 것과 동시에 min과 max값 찾기
			for (int i=0;i<10;i++) {
                int num = sc.nextInt();
				nums.add(num);
                if (min > num) {
                	min = num;
                }
                if (max < num) {
                	max = num;
                }
                avg += num;
            }
            // 평균 구하기
            avg -= min;
            avg -= max;
            avg /= 8;
            int iavg = Math.round(avg);
                
            System.out.println("#" + test_case +" " + iavg);
		}
	}
}