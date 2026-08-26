package week05.곽정민;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2805_농작물수확하기 {
  static int calculateValue(int arr[][], int size) {
    int value = 0;
    int center = size / 2;

    for (int row = 0; row < size; ++row) {

      if (row <= center) {
        for (int col = center - row; col <= center + row; ++col) {
          value += arr[row][col];
        }
      } else {
        int mirroredRow = size - 1 - row;   // 중심 기준 아래쪽은 위쪽과 대칭이라 아래에서부터 잰 거리로 계산
        for (int col = center - mirroredRow; col <= center + mirroredRow; ++col) {
          value += arr[row][col];
        }
      }
    }

    return value;
  }

  static int calculateValueByWidth(int arr[][], int size) {
    int value = 0;
    int center = size / 2;

    for (int row = 0; row < size; ++row) {
      int width = center - Math.abs(row - center);   // 중심에서 멀어질수록 좁아짐
      for (int col = center - width; col <= center + width; ++col) {
        value += arr[row][col];
      }
    }

    return value;
  }

  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = Integer.parseInt(br.readLine().trim());
    for(int tc = 1; tc<=test_case; tc++){
      int size = Integer.parseInt(br.readLine().trim());

      int [][] arr = new int[size][size];
      for(int i = 0; i<size; ++i){
        String line = br.readLine();
        for(int j = 0; j<size; ++j){
          arr[i][j] = line.charAt(j) - '0';
        }
      }

      int value = calculateValue(arr, size);
      int valueByWidth = calculateValueByWidth(arr, size);
      System.err.println("#" + tc + " calculateValue=" + value + " calculateValueByWidth=" + valueByWidth
          + (value == valueByWidth ? " (일치)" : " (불일치!)"));

      sb.append("#").append(tc).append(" ").append(value).append("\n");
    }

    System.out.print(sb);
  }
}
