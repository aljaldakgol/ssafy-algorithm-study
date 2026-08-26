import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		// 0)기본 setting
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <=testCase ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int mapSize = Integer.parseInt(st.nextToken());
			int slopeLen = Integer.parseInt(st.nextToken());
			int slopeCount=0;
			boolean isSlope;
			boolean isAllSame;
			
			int [][] map = new int[mapSize][mapSize];
			
			for (int i = 0; i< mapSize; i++) {
				
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j<mapSize; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1) 기본적으로 활주로를 만들 수 있음 - 만들 수 없는 경우는 ? 1. 한 번에 2칸이상 줄어든다 2. 한 칸 줄기는 했는데 줄어 든 후 그 수가 반복되는 게 길이보다 작다. 
			// 줄어들거나 늘거나 인데 그럼 차이를 절댓값으로 쓰면 되겠다. 
			// false 가 나오면 그 줄은 더 검사할 거 없이 넘어간다. 
			/**
			 * 생각 정리..
			 * 높이가 줄어들면 줄어든 상태로 x번 이상 계속 그 상태 유지 해야 하고
			 * 높이가 늘어나면 늘어나기 전 상태로 x번 이상 그 상태를 유지하고 있었어야 한다. 
			 * 그 높이는 한 칸 이상 늘어나서는  안된다. 
			 * 
			 * 즉 높이가 두칸 이상 벌어지거나
			 * 높이가 한 칸 늘어났는데 그 전에 x번 이상 반복되지 않았거나
			 * 높이가 한 칸 줄어들었는데 그 상태 값이 x번 이상 반복되지 않는다면
			 * 경사로가 만들어지지 않는다.
			 * 
			 * 예외 케이스를 처리하는 게 좋은가 적합한 케이스를 세는 게 좋을까
			 * 
			 * 또한 절댓값으로는 못 할 거 같다. 두 경우에 처리하는 법이 달라 보인다. 
			 * 
			 * 1) 두 칸 이상 차이 나면 더 볼 거 없다
			 * 2) 한 칸 줄어든다 -> 그 다음부터 몇 번 그게 반복되는지 센다 근데 ... 그럼 그 지점에서부터 다시 for문을 돌아야 한다는 건가?
			 * 3) 한 칸 늘어난다 -> 그 전에 그게 몇 번 반복되었는지 센다.... 그럼 그 지점부터반대 방향으로 for문??/? 
			 * 일단 go 단, map의 범위를 넘어서지 않아야 하는데....
			 * 
			 */
			// 행 별, 열 별로 다 봐야 햔다.

			// 행 별
			/**
			 * 중복 카운트 제거를 어떻게 해야 할 지 모르겠따. 경사로로 이미 확정된 인덱스가 반대쪽에서 경사로가 되면 안되는데...
			 */
			for(int k = 0; k<mapSize; k++) {
				boolean [] slopeVisited = new boolean[mapSize];

				isSlope = true;
				isAllSame = true;
				
				for (int l = 0; l<mapSize-1; l++) {
					//1) 두 칸 이상 차이 -> 경사로 불가능 볼거 없음
					if(Math.abs(map[k][l]-map[k][l+1])>1) {isAllSame=false; isSlope = false; break;}
					
					// 2) 한 칸 증 또는 감
					int xCount;	// 증 또는 감소한 상태로 얼마나 지속?
					int check;	// 얼마나 뒤/앞로 갈 수 있는지 체크
					
					// 2-1) 한 칸 증가
					if(map[k][l+1]-map[k][l]==1) {
						isAllSame=false;
						check = 1;		// 뒤쪽으로 방문한다 -얼마나 뒤로 갈 수 있는지 체크
						xCount = 1; 		// 일단 감소했던 게 하나 있고 지금까지 얼마나 그 상태가 지속 되었는가
						slopeVisited[l]= true;
						while((l-check)>=0&& !slopeVisited[l-check]) {		// 언제까지 뒤로 가는데
							if(map[k][l-check]==map[k][l]) { xCount++; check++;}
							
							else {break;}	// 다른 경사가 나왔어? 그럼 그만 가
							if(xCount==slopeLen) {break;}
						}
						if (xCount<slopeLen) {isSlope=false; break;}	// 길이짧네 경사로 불가능
					}
					
					// 2-2) 한 칸 감소
					if(map[k][l]-map[k][l+1]==1) {
						isAllSame=false;
						check = 1;
						xCount = 1;
						while((l+1+check)< mapSize) {
							if(map[k][l+1+check] == map[k][l+1]) { slopeVisited[l+1+check]= true; xCount++; check++;}
							else {break;}
							if(xCount==slopeLen) {break;}

						}
						
						if(xCount<slopeLen) {isSlope=false; break;} // 길이짧네 경사로 불가능
					}
					
					// 3) 근데 다 동일한 값 즉 불변이 있어?
//					
				}
				
				if(isSlope||isAllSame) {slopeCount++;}
				
			}
			
			// 열 별
			for(int k = 0; k<mapSize; k++) {
				boolean [] slopeVisited = new boolean[mapSize];

				isSlope = true;
				isAllSame = true;

				for (int l = 0; l<mapSize-1; l++) {
					//1) 두 칸 이상 차이
					if(Math.abs(map[l][k]-map[l+1][k])>1) {isAllSame=false; isSlope = false; break;}
					
					// 2) 한 칸 증 또는 감
					int xCount;	// 증 또는 감소한 상태로 얼마나 지속?
					int check;	// 얼마나 뒤/앞로 갈 수 있는지 체크
					
					// 2-1) 한 칸 증가
					if(map[l+1][k]-map[l][k]==1) {
						isAllSame=false;
						check = 1;		// 뒤쪽으로 방문한다
						xCount = 1; 		// 일단 감소했던 게 하나 있고 지금까지 얼마나 그 상태가 지속 되었는가
						slopeVisited[l]= true;

						while((l-check)>=0&&!slopeVisited[l-check]) {		// 언제까지 뒤로 가는데
							if(map[l-check][k]==map[l][k]) { xCount++; check++;}
							else { break;}	// 다른 경사가 나왔어? 그럼 그만 가
							if(xCount==slopeLen) {break;}
						}
						if (xCount<slopeLen) {isSlope=false; break;}	// 길이짧네 경사로 불가능
					}
					
					// 2-2) 한 칸 감소
					if(map[l][k]-map[l+1][k]==1) {
						isAllSame=false;
						check = 1;
						xCount = 1;
						while((l+1+check)<mapSize) {
							if(map[l+1+check][k]==map[l+1][k]) {slopeVisited[l+1+check]= true; xCount++; check++;}
							else {break;}
							if(xCount==slopeLen) {break;}

						}
						if(xCount<slopeLen) {isSlope=false;break;} // 길이짧네 경사로 불가능
					}					
				}
				
				if(isSlope||isAllSame) {slopeCount++;}
				
			}
			
			System.out.println("#"+tc+" "+slopeCount);
		}
		
	}

}

