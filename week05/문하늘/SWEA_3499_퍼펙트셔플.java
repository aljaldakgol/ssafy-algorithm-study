package week05;
import java.util.*;
import java.io.*;

public class SWEA_3499_퍼펙트셔플 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc<=testCase; tc++) {
			int cardNum = Integer.parseInt(br.readLine().trim());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Queue <String> arrQue1 = new ArrayDeque<>();
			Queue <String> arrQue2 = new ArrayDeque<>();
			
			for(int i =0; i<cardNum; i++) {
				
				if(cardNum%2==0) {
					if(i<cardNum/2) {
						arrQue1.add(st.nextToken()); 
					}else {
						arrQue2.add(st.nextToken()); 
					}
				}else {
					if(i<cardNum/2+1) {
						arrQue1.add(st.nextToken()); 
					}else {
						arrQue2.add(st.nextToken()); 
					}
				}
			}
			System.out.print("#" + tc+" ");
			while(true) {
				String finStr = arrQue1.poll();
				if(finStr==null) {break;}
				System.out.print(finStr);
				
				finStr = arrQue2.poll();
				if(finStr==null) {break;}
				System.out.print(" " + finStr + " ");
				
			}
			System.out.println();
			
		}
	}

}
