//package week03;
//
//import java.io.*;
//import java.util.*;
//
//
//public class SWEA_1234_비밀번호 {
//
//	@SuppressWarnings("null")
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		// 1) 10개의 테스트 케이스가 주어짐
//        
//        for (int tc = 0; tc<1; tc++) {
//        	
//        	// 2) 문자열의 개수와 문자열이 주어짐 
//        	StringTokenizer st = new StringTokenizer(br.readLine());
//
//        	int num = Integer.parseInt(st.nextToken());	// 문자열구성 숫자 개수 num
//        	String numString = st.nextToken(); // 문자열 받아서 numString에 
//        	
//        	char[] charArr = numString.toCharArray();
//        	
//        	
//            // 3) 문자열을array로 저장하고 1라운드로 인덱스 for 문으로 돌면서 인접한 동일 숫자 제거
//            //for (int i = 0; i<num-1; i++) { -> 이 방법은 아닌 
//            // **) 자바에서 배열에서 제거하면 ..? 인덱스 안댕겨지는데 이를 어떻게해결할 수 있을까 그리고 세 개의 동일한 숫자가 연달아 나오면 .. i를 잘조절해야 하는
//            // 두 개의 인접한 문자가나오는 경우 그 다음에 인덱스를 2개 점프해도 되고 인접하지 않은 경우 하나만 점프해야 할 거 같다. 
//            // 4) 2라운드 인접한 동일 숫자 제거 : 더이상 제거할 게 없으면 라운드종료
//
//            boolean roundcheck = true;	//삭제한 게 하나라도 있는 라운드는 true로 간다. 삭제 하나도 안했으면 false다. 그래서 true이면 다음라운드를 진행한다.
//            while (roundcheck) {
//                int i = 0;
//            	roundcheck = false;
//            	while (i<num-1) {
//            		if (charArr[i]==charArr[i+1]) { // 같으면 2개 점프해야 하고 또 삭제도 해야 하는데... 음... 제거해야 하는게 i, i+1의 문자들이니까..
//            			if(i<num-3) {
//            				for (int j = i; j<num-2; j++) {
//                				charArr[j]=charArr[j+2];
//                			}
//            				i=i+2;
//            				num=num-2;
//            			}
//            			else if (i == num-3 ){	//인덱스 num-3랑 num-2의 문자가 같으면 
//            				
//            				charArr[i]=charArr[i+2];
//            				i=i+2;
//            				num=num-2;
//
//            			}
//            			else if (i==num-2) {
//            				num=num-2;
//            				charArr = Arrays.copyOf(charArr, num);
//            				i++;
//            			}
//            			roundcheck=true;	//제거한 게 있으니까 다음라운드 진행 가
//            		}
//            		else { // 다르면 하나 다음 문자 비교하기도 하고 방금 꺼를 삭제하면 안되니까 ..
//            			i++;
//            			// 제거한 거 없음다음 라운드 없
//            		}
//            	}
//            }
//        	
//      
//            //최종 문자배열을 string으로 바꿔야 하
//           String finalString = String.valueOf(charArr);
//            
//            // 5) 최종 문자열이 비밀번호
//           System.out.printf("#%d %s", tc+1, finalString);
//        }
//		
//	}
//
//}

/**
 * 다시...!!!! 후,, 엉켰는데 일단 위에 꺼 냅두기..
 */
package week03;

import java.io.*;
import java.util.*;


public class SWEA_1234_비밀번호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1) 10개의 테스트 케이스가 주어짐
        
        for (int tc = 0; tc<10; tc++) {
        	
        	// 2) 문자열의 개수와 문자열이 주어짐 
        	StringTokenizer st = new StringTokenizer(br.readLine());

        	int num = Integer.parseInt(st.nextToken());	// 문자열구성 숫자 개수 num
        	String numString = st.nextToken(); // 문자열 받아서 numString에 
        	
        	char[] charArr = numString.toCharArray();
        	
        	
            // 3) 문자열을array로 저장하고 1라운드로 인덱스 for 문으로 돌면서 인접한 동일 숫자 제거
           // 라운드 돌지 말고 한번에 가
 
            int i = 0;
        	while (i<num-1) {
        		if (charArr[i]==charArr[i+1]) { // 같으면 2개 점프해야 하고 또 삭제도 해야 하는데... 음... 제거해야 하는게 i, i+1의 문자들이니까..
    				for (int j = i; j<num-2; j++) {
        				charArr[j]=charArr[j+2];
        			}
    				num=num-2;
        			
    				if (i > 0) i = i - 1; //삭제 전 인덱스부터 다시 체크 

        		}
        		
        		else { // 다르면 하나 다음 문자 비교하기도 하고 방금 꺼를 삭제하면 안되니까 ..
        			i++;
        		}
        	}
        	
      
            //최종 문자배열을 string으로 바꿔야 하
        	String finalString = new String(charArr, 0, num);
        	
            // 5) 최종 문자열이 비밀번호
           System.out.printf("#%d %s\n", tc+1, finalString);
        }
		
	}

}

