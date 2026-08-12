package week03.곽정민;

/*
 * [풀이 도움 여부]
**  자력해결
 *
 * [나의 생각 과정]
 * 1. 입력을 2차원 배열로 읽어들인 뒤, 출발점(2)에서 DFS로 도착점(3)까지 갈 수 있는지 확인하려고 했다.
 * 2. checkPath(map, checkedMap, x, y) 형태로 현재 좌표에서 재귀적으로 탐색하는 함수를 설계했다.
 *
 * [막힌 부분]
 * 이동 가능 여부(범위, 방문 여부, 벽)를 확인하는 로직을 checkValid로 분리하는 과정에서
 * map 파라미터를 빠뜨려 벽(1)을 검사하지 못하는 실수를 했다.
 *
 * [참고한 내용]
 * 전에 했던 햄버거 다이어트에서의 dfs부분 참고 
 *
 * [최종적으로 이해한 해결 방법]
 * checkValid에 map 파라미터를 추가해 범위, 방문 여부, 벽 여부를 모두 검사하도록 수정했고,
 * 시작점(2)의 좌표를 입력을 읽으면서 함께 찾아 DFS의 시작 좌표로 사용했다.
 */

import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.lang.Exception;

public class SWEA_1226_미로찾기1 {

    static final int size = 16;
    static final int[] dx = { 0, 0, 1, -1 };
    static final int[] dy = { -1, 1, 0, 0 };

    static boolean checkValid(int x, int y, boolean[][] checkMap, int[][] map) {

        if (x < 0 || x >= size || y < 0 || y >= size)
            return false;
        if (checkMap[y][x] == true)
            return false;

        if(map[y][x] == 1)
            return false;


        return true;

    }

    static boolean checkPath(int[][] map, boolean[][] checkedMap, int x, int y) {

        if (map[y][x] == 3) {
            return true;
        }

        checkedMap[y][x] = true;

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (checkValid(nx, ny, checkedMap,map)) {

                if (checkPath(map, checkedMap, nx, ny)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 0; tc < 10; ++tc) {

            int test_case = Integer.parseInt(br.readLine());
            int[][] map = new int[size][size];
            boolean[][] checked_map = new boolean[size][size];
            int startX = 0, startY = 0;

            for (int i = 0; i < size; i++) {
                String line = br.readLine();
                for (int j = 0; j < size; j++) {
                    map[i][j] = line.charAt(j) - '0';
                    if (map[i][j] == 2) {
                        startY = i;
                        startX = j;
                    }
                }
            }

            int result = checkPath(map, checked_map, startX, startY) ? 1 : 0;
            System.out.println("#" + test_case + " " + result);
        }
    }
}