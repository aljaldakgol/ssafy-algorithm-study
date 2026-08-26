// [입력]

// 입력은 첫 줄에 총 테스트 케이스의 개수 T가 온다.

// 다음 줄부터 각 테스트 케이스가 주어진다.

// 테스트 케이스의 첫 번째 줄에는 단어 퍼즐의 가로, 세로 길이 N 과, 단어의 길이 K 가 주어진다.

// 테스트 케이스의 두 번째 줄부터 퍼즐의 모양이 2차원 정보로 주어진다.

// 퍼즐의 각 셀 중, 흰색 부분은 1, 검은색 부분은 0 으로 주어진다.


// [출력]

// 테스트 케이스 t에 대한 결과는 “#t”을 찍고, 한 칸 띄고, 정답을 출력한다.

// (t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)



package week04.곽정민;
import java.io.*;
import java.util.*;


public class SWEA_1979_어디에단어가들어갈수있을까  {

  static int checkword(int [][] arr, int wordLen){
    int count = 0;
    int size = arr.length;

    // 가로 방향: 각 행에서 연속된 1의 구간 길이가 정확히 wordLen일 때만 카운트
    for(int i = 0; i<size; ++i){
      int run = 0;
      for(int j = 0; j<size; ++j){
        if(arr[i][j] == 1){
          run++;
        }else{
          if(run == wordLen) count++;
          run = 0;
        }
      }
      if(run == wordLen) count++;   // 행 끝까지 이어진 구간 처리
    }

    // 세로 방향: 각 열에서 연속된 1의 구간 길이가 정확히 wordLen일 때만 카운트
    for(int j = 0; j<size; ++j){
      int run = 0;
      for(int i = 0; i<size; ++i){
        if(arr[i][j] == 1){
          run++;
        }else{
          if(run == wordLen) count++;
          run = 0;
        }
      }
      if(run == wordLen) count++;   // 열 끝까지 이어진 구간 처리
    }

    return count;
  }

  public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
  int test_case = Integer.parseInt(br.readLine());

    for(int tc = 0; tc<test_case; tc++){
      StringBuilder sb = new StringBuilder();
      int count = 0;

      // 한 줄 읽어와서 공백 기준으로 나누기
			StringTokenizer st = new StringTokenizer(br.readLine());

      int Size = Integer.parseInt(st.nextToken());
      int wordLen = Integer.parseInt(st.nextToken());
      int [][] arr = new int[Size][Size];

      for(int i = 0; i < Size;++i){
        StringTokenizer rowSt = new StringTokenizer(br.readLine());
        for(int j = 0; j<Size; ++j){
          arr[i][j] = Integer.parseInt(rowSt.nextToken());   //0 검은색(못가는곳) 1 흰색(넣을수있는곳)
        }
      }
      count = checkword(arr,wordLen);

      

      sb.append("#" + (tc+1)+" " + count);
      System.out.println(sb);
    }

  }

}
