import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

class Solution
{
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

        // 각 테케 반복
		for(int test_case = 1; test_case <= T; test_case++)
		{
            boolean status = true;
           	List<Integer> arr = new ArrayList<>();
            List<List<Integer>> list = new ArrayList<>();
            int k = 0;
            // 배열에 값 넣으면서 가로 체크
            for (int i=0;i<9;i++) {
            	for (int j=0;j<9;j++) {
                    k = sc.nextInt();
                    if (arr.contains(k)) {
                        status = false;
                    }
                	arr.add(k);
                }
                list.add(arr);
                arr = new ArrayList<>();
            }
            
            // 세로 체크
            if (status == true) {
            	for (int i=0;i<9;i++) {
            	arr.add(list.get(0).get(i));
                arr.add(list.get(1).get(i));
                arr.add(list.get(2).get(i));
                arr.add(list.get(3).get(i));
                arr.add(list.get(4).get(i));
                arr.add(list.get(5).get(i));
                arr.add(list.get(6).get(i));
                arr.add(list.get(7).get(i));
                arr.add(list.get(8).get(i));
                Set<Integer> set = new HashSet<>(arr);
                if (set.size()<9) {
                    status = false;
                    break;
                }
                arr = new ArrayList<>();
           		}
            }
            
            // 3x3 체크
            if (status == true) {
            	for (int i=0;i<9;i+=3) {
            		for (int j=0;j<9;j+=3) {
                		arr.add(list.get(i).get(j));
                    	arr.add(list.get(i).get(j+1));
                    	arr.add(list.get(i).get(j+2));
                    	arr.add(list.get(i+1).get(j));
                    	arr.add(list.get(i+1).get(j+1));
                    	arr.add(list.get(i+1).get(j+2));
                    	arr.add(list.get(i+2).get(j));
                    	arr.add(list.get(i+2).get(j+1));
                    	arr.add(list.get(i+2).get(j+2));
                    	Set<Integer> set = new HashSet<>(arr);
	                	if (set.size()<9) {
            		        status = false;
                    		break;
                		}
                		arr = new ArrayList<>();
            			}
                	}
            }
            
			if (status == true) {
            	System.out.println("#"+test_case+" "+"1");
            } else {
            	System.out.println("#"+test_case+" "+"0");
            }

		}
	}
}