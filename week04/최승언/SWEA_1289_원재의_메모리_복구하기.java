import java.io.*;

/*
 * 문제: SWEA 1289 원재의 메모리 복구하기
 * 메모리: 25,472 kb
 * 실행 시간: 72 ms
 * 알고리즘: 구현
 */

public class SWEA_1289_원재의_메모리_복구하기 {

	static int T;
	static int[] bit;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			String sBit = br.readLine();
			
			bit = new int[sBit.length()];
			for(int i = 0; i < sBit.length(); i++) {
				bit[i] = sBit.charAt(i) - '0';
			}
			
			int[] ansBit = new int[bit.length];
			int ans = 0;
			for(int i = 0; i < bit.length; i++) {
				if(bit[i] != ansBit[i]) {
					for(int j = i; j < bit.length; j++) {
						ansBit[j] = bit[i];
					}
					ans++;
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

}
