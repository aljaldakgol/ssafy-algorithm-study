package week05.곽정민;

import java.util.*;
import java.io.*;

//각 테스트 케이스의 첫 줄에는 지하 터널 지도의 세로 크기 N, 가로 크기 M,
// 맨홀 뚜껑이 위치한장소의 세로 위치 R, 가로 위치 C, 그리고 탈출 후 소요된 시간 L 이 주어진다.
public class SWEA_1953_탈주범검거 {

    // 상, 하, 좌, 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[] opposite = {1, 0, 3, 2};

    // open[type][dir] : 해당 타입이 그 방향으로 열려있는지
    // 여기가 원래 막혔던 부분 -> "파이프가 어떤 조건이어야 연결되는지"를 못 정했었음
    static boolean[][] open = {
        {},                                 // 0: 터널 없음
        {true, true, true, true},          // 1: 십자
        {true, true, false, false},        // 2: 세로 I
        {false, false, true, true},        // 3: 가로 =
        {true, false, false, true},        // 4: 상,우
        {false, true, false, true},        // 5: 하,우
        {false, true, true, false},        // 6: 하,좌
        {true, false, true, false},        // 7: 상,좌
    };

    static int mapRow, mapCol;

    // 4,5 같은 자리 / 5,4 같은 자리를 구분 못했던 이유 ->
    // 내 쪽이 그 방향으로 열려있는지만 봤지, 상대 칸이 반대 방향으로 열려있는지는 안 봤기 때문.
    // 두 조건을 둘 다 만족해야 연결이라서, 아래 두 줄을 순서대로 통과해야 true가 됨.
    static boolean isConnected(int curType, int nextType, int dir) {
        if (!open[curType][dir]) return false;              // 내가 이 방향으로 안 열려있으면 바로 끝
        if (!open[nextType][opposite[dir]]) return false;    // 상대가 반대 방향으로 안 열려있어도 끝
        return true;                                          // 둘 다 열려있을 때만 연결
    }

    // 원래 계획대로 재귀(배열, 현재시간, 제한시간 넘기며 상하좌우 비교)로 짜도 똑같이 풀림.
    // 여기선 큐에 (y, x, 현재시간)을 넣는 BFS로 같은 걸 반복문으로 구현한 것뿐.
    static int bfs(int[][] map, int startRow, int startCol, int limit) {
        boolean[][] visited = new boolean[mapRow][mapCol];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;
        int count = 1; // 시작 칸 포함

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0], x = cur[1], time = cur[2];

            if (time == limit) continue; // 더 이상 이동할 시간이 없음

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                // 여기 세 줄이 처음에 생각했던 "배열 밖 / 이미 visited / 0(빈 공간)이면 종료" 부분
                if (ny < 0 || ny >= mapRow || nx < 0 || nx >= mapCol) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx] == 0) continue;
                // 여기가 그때 못 정했던 "파이프 연결 조건" 체크. isConnected()에서 처리
                if (!isConnected(map[y][x], map[ny][nx], dir)) continue;

                visited[ny][nx] = true;
                count++;
                queue.add(new int[]{ny, nx, time + 1});
            }
        }

        return count;
    }

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int test_case = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= test_case; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mapRow = Integer.parseInt(st.nextToken());
            mapCol = Integer.parseInt(st.nextToken());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            int[][] map = new int[mapRow][mapCol];
            for (int i = 0; i < mapRow; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < mapCol; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = bfs(map, startRow, startCol, time - 1);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.print(sb);
    }

}
