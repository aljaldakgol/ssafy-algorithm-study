import java.util.Scanner;
import java.util.ArrayList;
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
			int K = sc.nextInt();
            
			Double KScore = 0.0;
			ArrayList<Double> arScore = new ArrayList<>(N);

			// 점수 입력
			for (int i = 0; i < N; i++) {
    			double s = sc.nextInt() * 0.35+ sc.nextInt() * 0.45+ sc.nextInt() * 0.20;
    			arScore.add(s);

    			if (i == K - 1) {    // K는 1번부터 시작
        			KScore = s;
    			}
			}

			// K학생의 위치

			Collections.sort(arScore, Collections.reverseOrder());

			int idx = arScore.indexOf(KScore);

			int g = idx / (N / 10);

			String grade ="";

			switch(g) {
    			case 0:
        			grade = "A+";
        			break;
    			case 1:
        			grade = "A0";
        			break;
    			case 2:
        			grade = "A-";
        			break;
    			case 3:
        			grade = "B+";
        			break;
    			case 4:
        			grade = "B0";
        			break;
    			case 5:
        			grade = "B-";
        			break;
    			case 6:
        			grade = "C+";
        			break;
    			case 7:
        			grade = "C0";
        			break;
    			case 8:
        			grade = "C-";
        			break;
    			case 9:
        			grade = "D0";
        			break;
			}

			System.out.println("#" + test_case + " "  + grade);       
            } 
		}
	}