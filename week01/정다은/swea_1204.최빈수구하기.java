import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
        	int tc = sc.nextInt();

    		int[] arr = new int[1000];

    		// 점수 입력
    		for(int i = 0; i < 1000; i++) {
        		arr[i] = sc.nextInt();
    		}	

    		Arrays.sort(arr);

    		int time = 0;
    		int ctime = 1;
    		int v = arr[0];

    		for(int i = 1; i < arr.length; i++) {

        		if(arr[i] == arr[i - 1]) {
            		ctime++;
        		} else {
            		if(ctime > time) {
                		time = ctime;
                		v = arr[i - 1];
            		} else if(ctime == time && arr[i - 1] > v) {
                		v = arr[i - 1];
            		}
            		ctime = 1;
        		}
    		}

    		// 마지막 숫자 처리
    		if(ctime > time) {
        		v = arr[arr.length - 1];
    		} else if(ctime == time && arr[arr.length - 1] > v) {
        		v = arr[arr.length - 1];
    		}

    		System.out.println("#" + tc + " " + v);
        }
	}
}