import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

class Solution
{
    static int answer = 0;
    static List<Integer> nums = new ArrayList<>();
    static int K;
    static int N;
    public static void sumK(int idx, int sum, int count) {
        // 합이 K 이상일 때
    	if (sum > K) {
        	return;
        }
        // 합이 K일 때
        if (sum == K) {
        	if (count > 0) {
            	answer++;
            }
            return;
        }
        // 모든 원소 확인
        if (idx == N) {
        	return;
        }
        // 현재 숫자 선택
        sumK(idx+1, sum + nums.get(idx), count+1);
        
        // 현재 숫자 미선택
        sumK(idx+1, sum, count);
    }
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{	
            answer =0;
            nums.clear();
            // 개수
			N = sc.nextInt();
            // 목표 합
            K = sc.nextInt();
            for(int i=0;i<N;i++) {
            	nums.add(sc.nextInt());
            }
            sumK(0,0,0);
            System.out.println("#"+test_case+" "+answer);
		}
	}
}