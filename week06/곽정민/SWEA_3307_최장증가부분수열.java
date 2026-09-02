import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3307_최장증가부분수열 {

  public static void main(String args[]) throws IOException
  {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = Integer.parseInt(br.readLine().trim());
    for(int tc = 1; tc<= test_case; ++tc){
      int Num = Integer.parseInt(br.readLine().trim());
      int[] a = new int[Num];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i = 0; i<Num; ++i){
        a[i] = Integer.parseInt(st.nextToken());
      }

      // dp[i] = a[i]를 마지막 원소로 골랐을 때 만들 수 있는 증가 부분 수열의 최대 길이
      int[] dp = new int[Num];
      int answer = 0;
      for(int i = 0; i<Num; ++i){
        // 앞에서 아무것도 못 이어붙이더라도, a[i] 하나만 고른 목록은 항상 만들 수 있으므로 최소값 1
        dp[i] = 1;

        // i보다 앞에 있는 원소들(j) 중에서 a[i] 앞에 이어붙일 수 있는 후보를 전부 검사
        for(int j = 0; j<i; ++j){
          if(a[j] < a[i]){
            // a[j]가 a[i]보다 작으니, j에서 끝나는 목록(dp[j]개) 뒤에 a[i] 자신을 하나 추가할 수 있음 → dp[j] + 1
            // 지금까지 찾은 dp[i](다른 j로 이미 만들어둔 더 긴 목록)보다 클 때만 갱신
            dp[i] = Math.max(dp[i], dp[j] + 1);
          }
        }

        // 전체 정답은 특정 i에서 끝나는 게 아니라, 모든 dp[i] 중 최댓값
        answer = Math.max(answer, dp[i]);
      }

      sb.append('#').append(tc).append(' ').append(answer).append('\n');
    }

    System.out.print(sb);
  }

}
