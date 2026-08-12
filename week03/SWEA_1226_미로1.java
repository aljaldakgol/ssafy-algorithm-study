/**
 * 미로 찾기는 BFS(너비 우선 탐색)를 사용하는 것이 가장 직관적이고 깔끔합니다.
 * BFS를 쉽게 이해하기 (줄 서서 탐색하기)
 * 
 * 줄 서기(Queue, 큐): 내가 앞으로 방문할 미로 칸들을 순서대로 적어두는 '대기열'을 하나 만듭니다.
 * 도장 찍기(visited 배열): 이미 지나간 길에 다시 돌아가지 않도록 '방문 표시'를 해둡니다.
 * 4방향 상하좌우 탐색: 현재 위치에서 위, 아래, 왼쪽, 오른쪽을 둘러보며 "벽이 아니고(0 또는 3), 아직 안 가본 길"이면 대기열에 집어넣습니다.
 * 반복하다가 도착점(3)을 만나면 1(가능)을 출력하고, 더 이상 갈 곳이 없을 때까지 못 만나면 0(불가능)을 출력합니다.
 * 
*/

/**
 * 이전 코드 문제점
 * 1) StringTokenizer로 입력 읽으려고 했다. 
 * 미로 데이터는 공백 없이 들어오는데 StringTokenizer는 띄어쓰기 기준으로 잘라내기 때문에 안된다.
 * 해결 방법 : String line = br.readLine().trim();으로 한 줄 통째로 읽고 line.charAt(j)-'0'으로 한 글자씩 쪼개자
 * 
 * 2) 어디서 본 거는 있어서 dx dy를 썼는데 로직이 문제
 * 이중 for문을 하면 4*4 중첩 루프라 16번이다.단일 for문을 돌려야 한다. 
 * 
 * 3) 방문 체크 누락. <- 나는 근데 이거는 길이 막히면 돌아가야 하니까 이전 길로 돌아가야 한다고 생각했다.
 * 
 * 4) queue (대기열) : BFS, DFS의 핵심은 내가갔던 길을 다시 가지 않도록 체크하는 것이고 앞으로 가야 할 후보를 대기열 (큐, 스택)에 모아 두는 것이다.
 * <- 사실 무한 루프에빠져서 어떻게 나가야 하나 고민이 많았는데 답을 못 내긴 했었다. 
 */
package week03;
import java.util.*;
import java.io.*;

public class SWEA_1226_미로1 {

	// 16* 16 크기의 미로 고정
	static final int N = 16;
	static int [][] maze;
	static boolean[][] visited;
	
	// 상하좌우 좌표
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	// 위치 정보 담기
	static class Point {
		int x, y;
		Point (int x, int y){
			this.x = x;
			this.y = y;
			
		}
	}
	
	
	// 메인함수 
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		for (int tcCount = 1 ; tcCount <= 10; tcCount++ ) {
			// 1) testcase 번호 읽
			
			int tc = Integer.parseInt(br.readLine().trim());
			
			// 2) 미로 찾고 출발점,도착점을 찾는다. 
			maze = new int[N][N];
			visited = new boolean[N][N];
			
			int startY = -1;	// 출발지 i
			int startX = -1;	// 출발지 j
			
			for (int i = 0; i<16; i++) {
				String line = br.readLine().trim();	// 한 줄씩 읽어야 하니까 for 문 하나 아래에 있어야지!!!

				for (int j = 0; j<16; j++) {
					maze[i][j]=line.charAt(j)-'0';

					// 입력 받으면서 출발점, 도착점찾자 (도착지 위치는 필요 없어 보인다)
					if (maze[i][j]==2) {
						startY = i;
						startX = j;
					} 
				}
			}
			
			// BFS 실행 및 결과 출력 
			int result = bfs(startX, startY);
			System.out.println("#" + tc + " " + result);
			
		}		
	}
	// BFS 
	public static int bfs(int startX, int startY) {
		Queue<Point> queue = new ArrayDeque<> ();
		// 1 . 시작점 큐에 넣고 방문
		queue.add(new Point(startX, startY));
		visited [startX][startY] = true;
		
		// 2. 큐가 빌 때까지 반복 (갈 수 있는 모든 경로 확인)
		while (!queue.isEmpty()) {
			Point current = queue.poll();
			
			// 4방향 탐색 (상, 하, 좌, 우)
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                // 미로 범위 안에 있는지 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {

                    // 도착점(3)을 찾은 경우 성공!
                    if (maze[nx][ny] == 3) {
                        return 1;
                    }

                    // 길(0)이고 아직 방문하지 않은 곳이라면
                    if (maze[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true; // 방문 도장 찍기
                        queue.add(new Point(nx, ny)); // 대기열에 추가
                    }
                }
            }
        }
		// 3. 끝까지 돌았는데 도착점 못찾으면 실
		return 0;
		
	}
}

