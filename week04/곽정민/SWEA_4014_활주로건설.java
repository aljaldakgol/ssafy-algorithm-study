package week04.곽정민;

import java.io.*;
import java.util.*;

public class SWEA_4014_활주로건설 {

  static int N, X;

  // 한 줄(행 또는 열)에 활주로를 놓을 수 있는지 검사
  static boolean checkLine(int[] line) {
    boolean[] used = new boolean[N];

    for (int i = 0; i < N - 1; i++) {
      int diff = line[i + 1] - line[i];

      if (diff == 0) continue;
      if (diff > 1 || diff < -1) return false;

      if (diff == 1) {
        // 오르막: line[i] 쪽이 낮음 -> 왼쪽으로 X칸이 평지(line[i] 높이)여야 함
        int start = i - X + 1;
        if (start < 0) return false;
        for (int k = start; k <= i; k++) {
          if (used[k] || line[k] != line[i]) return false;
        }
        for (int k = start; k <= i; k++) used[k] = true;
      } else {
        // 내리막: line[i+1] 쪽이 낮음 -> 오른쪽으로 X칸이 평지(line[i+1] 높이)여야 함
        int end = i + X;
        if (end >= N) return false;
        for (int k = i + 1; k <= end; k++) {
          if (used[k] || line[k] != line[i + 1]) return false;
        }
        for (int k = i + 1; k <= end; k++) used[k] = true;
      }
    }
    return true;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = Integer.parseInt(br.readLine().trim());

    for (int tc = 1; tc <= test_case; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());

      int[][] arr = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int count = 0;

      // 가로 방향 검사
      for (int i = 0; i < N; i++) {
        if (checkLine(arr[i])) count++;
      }

      // 세로 방향 검사
      for (int j = 0; j < N; j++) {
        int[] col = new int[N];
        for (int i = 0; i < N; i++) {
          col[i] = arr[i][j];
        }
        if (checkLine(col)) count++;
      }

      sb.append("#").append(tc).append(" ").append(count).append("\n");
    }

    System.out.print(sb);
  }
}
