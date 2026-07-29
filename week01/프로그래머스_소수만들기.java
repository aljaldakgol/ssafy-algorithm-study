package algorithm_study_week01;

public class 프로그래머스_소수만들기 {
	public static boolean isPrime3(int n) {
		if(n<2) {
			return false;
		}
		for (int i = 2; i*i <=n; i++) {
			if(n%i ==0) {
				System.out.print(i + " ");
				return false;
			}
		}
		return true;
	}
	
	public int solution(int[] nums) {
        int answer = -1;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }

}
