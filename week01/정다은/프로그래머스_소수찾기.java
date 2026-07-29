import java.util.ArrayList;

class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int cnt = 0;
        
        // 에라토스테네스의 체
        // boolean으로 변경 후 인덱스 값으로 소수 판별
        
        // 1. 배열 초기화
        boolean[] arr = new boolean[n+1];
        
        
        // 2. 하나씩 순회하며 지우기
        
        for(int i=2;i*i<=n;i++) {
            if (arr[i]==true) {
                continue;
            }
            for(int j=i+i;j<=n;j+=i) {
                if (arr[j] == false && j % i == 0) {
                    arr[j] = true;
                    cnt++;
                }
            }
        }
        answer = n-cnt-1;
        
        return answer;
    }
}
