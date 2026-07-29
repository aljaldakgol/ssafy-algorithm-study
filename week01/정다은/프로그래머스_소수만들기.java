class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        int n = nums.length;
        
        int total = 0;
        boolean b = true;
        // 경우의 수 배열 순회
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                for(int k=j+1;k<n;k++) {
                    total = nums[i]+nums[j]+nums[k];
                    b = true;
                    
                    for (int l=2;l*l<=total;l++) {
                        if (total % l == 0) {
                            b = false;
                            break;
                        }
                    }
                    if (b == true) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}
