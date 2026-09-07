import java.util.*;
import java.io.*;

public class SWEA_1859_백만장자프로젝트 {

  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = Integer.parseInt(br.readLine().trim());
    for(int tc = 1; tc<= test_case; ++tc){
      int numCount = Integer.parseInt(br.readLine().trim());
      int[] price = new int[numCount];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i = 0; i<numCount; ++i){
        price[i] = Integer.parseInt(st.nextToken());
      }

      long profit = 0;
      int max = 0;
      for(int i = numCount - 1; i >= 0; --i){
        if(price[i] >= max){
          max = price[i];
        } else {
          profit += max - price[i];
        }
      }

      sb.append('#').append(tc).append(' ').append(profit).append('\n');
    }

    System.out.print(sb);
  }

}
