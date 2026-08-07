import java.util.Scanner;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;

class Solution
{
    // 내가 못 함
    static boolean bfs(int[][] grid, int sx, int sy) {
		// 상하좌우 이동 위한 방향 배열
    	int[] dx = {-1, 1, 0, 0};
    	int[] dy = {0, 0, -1, 1};
        
		// 방문 여부 저장
    	boolean[][] visited = new boolean[16][16];
        
		// BFS에서 사용할 큐 생성
    	Queue<int[]> queue = new LinkedList<>();
        
        // 시작 위치 큐에 넣기
    	queue.offer(new int[] {sx, sy});
        
        // 시작 위치 방문 처리
    	visited[sx][sy] = true;
        
		// 큐가 빌 때까지 반복
    	while (!queue.isEmpty()) {
            
			// 현재 위치 큐에서 꺼냄
	        int[] cur = queue.poll();
			
    	    int cx = cur[0];
        	int cy = cur[1];
            
			// 현재 위치에서 상하좌우를 모두 확인
        	for (int i = 0; i < 4; i++) {
				// 다음 이동할 위치 계산
            	int nx = cx + dx[i];
            	int ny = cy + dy[i];
                
				// 범위 넘어가면 continue
            	if (nx < 0 || ny < 0 || nx >= 16 || ny >= 16)
	                continue;
				
                // 이미 방문한 곳이면 continue
    	        if (visited[nx][ny])
        	        continue;
                
				// 벽이면 continue
            	if (grid[nx][ny] == 1)
                	continue;
				
                // 3을 만나면 true return
            	if (grid[nx][ny] == 3)
                	return true;
				
                // 해당 위치 방문한걸로 체크
            	visited[nx][ny] = true;
                
                // 다음 탁색을 위해 큐에 추가
            	queue.offer(new int[] {nx, ny});
    	    }
    	}
        // 큐가 모두 비었는데도 도착점을 찾지 못한 경우
    	return false;
}
    // 여기부턴 직접 짬
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		

		for(int test_case = 1; test_case <= 10; test_case++)
		{
            int T=sc.nextInt();
            int[][] grid = new int[16][16];
            int sx = 0;
            int sy = 0;
            // 1. grid 배열 채우기 하면서 2 찾기
			for (int i=0;i<16;i++) {
                String row = sc.next();
            	for (int j=0;j<16;j++) {
                    int num = row.charAt(j) - '0';
                	grid[i][j] = num;
                    if (num == 2) {
                    	sx = i;
                        sy = j;
                    }
                }
            }
            
            // 2. bfs 사용
            boolean b = bfs(grid, sx, sy);
            if (b == true) {
            	System.out.println("#"+test_case+" 1");
            } else {
            	System.out.println("#"+test_case+" 0");
            }
		}
	}
}