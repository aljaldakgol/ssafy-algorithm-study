package week05.곽정민;

import java.util.*;
import java.io.*;
public class SWEA_3499_퍼펙트셔플   {

  static String[] perfectShuffle(String[] cards, int n) {
    String[] result = new String[n];
    int frontLen = (n + 1) / 2;   // n이 홀수면 앞쪽(front)에 한 장 더

    for (int i = 0; i < frontLen; i++) {
      result[2 * i] = cards[i];
    }
    for (int i = 0; i < n - frontLen; i++) {
      result[2 * i + 1] = cards[frontLen + i];
    }

    return result;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = Integer.parseInt(br.readLine().trim());

    for (int tc = 1; tc <= test_case; tc++) {
      int n = Integer.parseInt(br.readLine().trim());

      StringTokenizer st = new StringTokenizer(br.readLine());
      String[] cards = new String[n];
      for (int i = 0; i < n; i++) {
        cards[i] = st.nextToken();
      }

      String[] shuffled = perfectShuffle(cards, n);

      sb.append("#").append(tc);
      for (String card : shuffled) {
        sb.append(" ").append(card);
      }
      sb.append("\n");
    }

    System.out.print(sb);
  }
}
