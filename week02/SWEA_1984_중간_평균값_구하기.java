/**
* 메모리 : 27392kb
* 실행시간 : 112ms

*feedback : 반올림하는 방법을 알게 되었다. Math.round()
*/
import java.util.Scanner;
import java.io.FileInputStream;
import java.math.RoundingMode; 

class SWEA_1984_중간_평균값_구하기
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int[] arr= new int[10];
            for(int i = 0; i<10; i++) {
                arr[i]=sc.nextInt();
            }
            int min = arr[0];
            int max = arr[0];
            
            for (int j = 1; j<10; j++) {
                if (min>arr[j]) {
                    min = arr[j];
                }
                if (max<arr[j]) {
                    max = arr[j];
                }
			}
            
            int sum = 0;
            
            for (int k = 0; k< 10;k++) {
                sum += arr[k];
            }
            sum = sum - min - max;
            int avg=Math.round((float)sum/8);
            System.out.printf("#%d %d\n",test_case, avg);
        }
    }
}