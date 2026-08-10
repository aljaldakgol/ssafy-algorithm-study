import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

class Solution {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int N = sc.nextInt();
            int M = sc.nextInt();

            List<Set<Integer>> arr = new ArrayList<>();
            Set<Integer> people = new HashSet<>();
			
            // M번 반복
            for (int i = 0; i < M; i++) {

                int j = sc.nextInt();
                int k = sc.nextInt();

                people.add(j);
                people.add(k);

                boolean b = false;

                for (int l = 0; l < arr.size(); l++) {

                    if (arr.get(l).contains(j) || arr.get(l).contains(k)) {

                        arr.get(l).add(j);
                        arr.get(l).add(k);

                        b = true;
                        break;
                    }
                }

                if (!b) {
                    Set<Integer> set = new HashSet<>();

                    set.add(j);
                    set.add(k);

                    arr.add(set);
                }
            }

            // 겹치는 그룹 합치기
            for (int i = 0; i < M; i++) {

                for (int l = 0; l < arr.size() - 1; l++) {

                    for (int m = l + 1; m < arr.size(); m++) {

                        if (!Collections.disjoint(arr.get(l), arr.get(m))) {

                            arr.get(l).addAll(arr.get(m));
                            arr.remove(m);

                            m--;
                        }
                    }
                }
            }

            // 관계가 있는 그룹 + 관계에 등장하지 않은 사람
            int result = arr.size() + (N - people.size());

            System.out.println("#" + test_case + " " + result);
        }
    }
}