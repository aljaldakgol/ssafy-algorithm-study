import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_2117_홈방범서비스 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = Integer.parseInt(br.readLine().trim());
    for (int tc = 1; tc <= test_case; ++tc) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());

      int[][] map = new int[N][N];
      for (int i = 0; i < N; ++i) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; ++j) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int answer = Integer.MIN_VALUE;

      ArrayList<int[]> houseCoords = new ArrayList<>();
      int max_k =1;
      int totalHouseCount = 0;
       for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
         if(map[i][j] == 1){
          totalHouseCount++;
          houseCoords.add(new int[]{i, j});
         }
        }
      }
      while((max_k * max_k + (max_k -1) * (max_k - 1)) < M * totalHouseCount){
        max_k++;
      }

      for(int i = 1; i<max_k; ++i){
          answer = Math.max(profit(i, houseCoords, N, M),answer);
      }

     

      sb.append('#').append(tc).append(' ').append(answer).append('\n');
    }

    System.out.print(sb);
  }
  static int profit(int k, ArrayList<int[]> houseCoords, int N, int M){
    int cost = k * k + (k - 1) * (k - 1);
    int value = 0;

    // 모든 칸을 서비스 영역의 중심 후보로 시도
    for (int r = 0; r < N; ++r) {
      for (int c = 0; c < N; ++c) {
        int houseCount = 0;

        // 전체 격자 대신, 집이 있는 좌표만 순회하며 중심(r,c)과의 맨해튼 거리가 (k-1) 이하인지 확인
        for (int[] house : houseCoords) {
          if (Math.abs(house[0] - r) + Math.abs(house[1] - c) <= k - 1) {
            houseCount++;
          }
        }

        // 손해를 보지 않는 중심만 후보로 삼아 최댓값 갱신
        if (houseCount * M >= cost) {
          value = Math.max(value, houseCount);
        }
      }
    }

    return value;
  }


}
