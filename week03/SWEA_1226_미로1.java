 * 상황 : 2에서 출발해서 3으로 간다. 0만 따라간다. 1은 벽이라서 못간다. 도달 가능 시 1, 못하면 0
 * 방법 : 가다가 1을 만나면 0인 곳으로 방향을 바꾼다. 근데 지나온 길 외에 다 1이다? 그러면 되돌아간다. 그리고 선택한 0 외에 다른 0을 선택한다.
 * 더 이상 선택할 수 있는 0이 없으면 0을 반환한다.
 * 가다가 3을 만나면 1을 반환한다.
 * 
 * 1) 미로를 입력받는다. 2차원 배열이 되겠지? 
 * 2) 2를 찾는다. 거기가 시작점이다. 
 * 3) 사방에서 0을 찾아서 간다. 계속 0을 따라간다. 단, 방금 지나온 0 말고. 근데 이걸 어떻게 알지? 
 * 못가는 길 : 1이거나 방금 지나온 길 / 근데 잘못 진입한 경우이면 방금 지나온 길로돌아가긴해야지
 * 
*/
package week03;
import java.util.*;
import java.io.*;

public class SWEA_1226_미로1 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		for (int tcCount = 0 ; tcCount < 10; tcCount++ ) {
			// 1) testcase 번
			String tcLine = br.readLine();
			if(tcLine==null) break; // 예외 방지!
			int tc = Integer.parseInt(tcLine.trim());
			
			// 2) 미로 찾고 출발점,도착점을 찾는다. 
			
			int [][] maze = new int[16][16];
			int startY = 0;	// 출발지 i
			int startX = 0;	// 출발지 j
//			int destY = 0; 	// 도착지 i
//			int destX=0;	// 도착지 j
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i<16; i++) {
				for (int j = 0; j<16; j++) {
					maze[i][j]=Integer.parseInt(st.nextToken());

					// 입력 받으면서 출발점, 도착점찾자 (도착지 위치는 필요 없어 보인다)
					if (maze[i][j]==2) {
						startY = i;
						startX = j;
					} 
				}
			}
			
			// 3) 출발점에서 출발하자. 근데 이걸 언제까지 해야 하는 건가요???
			int [] dy = {1, -1, 0, 0};
			int [] dx = {0, 0, 1, -1};
			int curY = startY;
			int curX = startX;
			int visitCount = 0;
			
			boolean isPossible = false; 
			while (!isPossible) {
			
				for (int y = 0; y < 4; y++){
					for(int x = 0; x < 4; x++) {
						
						if (maze[curY+dy[y]][curX+dx[x]] == 0 ){
							curY += dy[y];
							curX += dx[x];
						}
						
						if (maze[curY+dy[y]][curX+dx[x]] == 3 ){
							isPossible = true;
							break;
						}
					}
				}
				
			}
			int isPossibleInt = 0;
			
			if (isPossible = false) {
				isPossibleInt = 0;
			}else {
				isPossibleInt = 1;
			}
			
			System.out.println("#"+ tc + " " + isPossibleInt);
		}		
		
	}
}