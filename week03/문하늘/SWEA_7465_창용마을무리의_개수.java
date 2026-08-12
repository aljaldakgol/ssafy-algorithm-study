// 누구끼리 같은 무리인지는 알 필요 없잖아???? 그냥 무리 수만 알면 되네?? 
// fail 된 이 유 : 둘 다 visited된 적 있는데 그 둘이 다른 무리였따면???? 그러면visited가 boolean이면 안되겠다. 
package week03;
import java.io.*;
import java.util.*;

public class SWEA_7465_창용마을무리의_개수 {
	static int N; //마을 사람 수
	static int acqNum; // 지인 관계 수
	static int muriNum;
	static int [] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		// T번의 테스트케이스
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 테스트케이스 당 인원수와 지인 관계 수
			N = Integer.parseInt(st.nextToken());
			acqNum = Integer.parseInt(st.nextToken());
			
			int one ;// 첫 번째 사람
			int another;// 두 번째 사람
			visited = new int[N];//테케 별 초기화  0
			muriNum = 0; // 무리 수 초기화 
			int idxMuri = 1; // 어떤 무리?
			
			for (int acN = 0; acN < acqNum; acN++) {
				st = new StringTokenizer (br.readLine());
				one = Integer.parseInt(st.nextToken());
				another = Integer.parseInt(st.nextToken());
				
				// 1) 한 번도 둘 다 무리에 들어간 적이 없다 -> 무리 수 하나 추가하고 둘을 같은 무리에 넣기
				if ((visited[one-1] == 0) && (visited[another-1] == 0)) {
					muriNum++;
					// 이제 무리에 들어 갔으니 
					visited[one-1] = idxMuri;
					visited[another-1] = idxMuri;
				}
				
				// 2) 둘 중 한 명만 들어있는 경우 -> 그 사람 무리 번호 따라가기
				else if (visited[one - 1] == 0) {
				    visited[one - 1] = visited[another - 1];
				} else if (visited[another - 1] == 0) {
				    visited[another - 1] = visited[one - 1];
				}
				
				// 3) 둘이 서로 다른 무리에 속해 있는 경우 두 무리 합치기
				// 여기가 문제.. !!! 
				// 1) 두 값이 같은 상태로 주어질 수도 있다는 것을 간과했음
				// 2) targetMuri 를 만들어 놓아야 했음 왜냐면 중간에 값이 바뀔수도 있는데  무리 합치는 순간 기준값이 변경
				else if(visited[one - 1] != visited[another - 1] ){
					muriNum--;
					int targetMuri = visited[another - 1]; 
					
				    for (int index = 0; index < N; index++) {
				        if (visited[index] == targetMuri) {
				            visited[index] = visited[one - 1];
				        }
				    }
				    
				}
				
				idxMuri++;	// 어떤 숫자인지에 대해서는 관심 없고 그냥 같은 무리라는 것을 표시	
				
			}
			
			
			// 총 무리 수 : muriNum + 한 번도 언급되지 않은 사람 수(neverVisitedNum)
			int neverVisitedNum = 0;
			
			for (int j = 0; j<N ;j++) {
				
				if(visited[j]==0) {
					neverVisitedNum++;
				}
			}
			muriNum += neverVisitedNum;
			
			// 결과 출력
			System.out.println("#" + tc + " " + muriNum);
			
		}
		
		
	}

}
