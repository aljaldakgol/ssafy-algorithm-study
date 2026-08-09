package week03.곽정민;

/*
 * [풀이 도움 여부]
 * 자력 해결 
 *
 * [나의 생각 과정]
 * 1. 서로 아는 사람들을 하나로 묶어야 하므로, ArrayList<Set<Integer>>로 그룹을 직접 관리해보려고 했다.
 * 2. 간선 (a, b)가 들어올 때 a나 b가 이미 속한 집합이 있으면 거기에 나머지를 추가하는 방식을 생각했다.
 *
 * [막힌 부분]
 * a와 b가 각각 서로 다른 두 집합에 이미 속해 있는 경우, 두 집합을 합치는 로직이 빠져 있었다.
 * 또한 어떤 관계도 없는 고립된 사람을 어떻게 카운트해야 할지 판단이 서지 않았다.
 *
 * [참고한 내용]
 * find/union 함수와 parent 배열을 1-indexed(N+1 크기)로 만드는 이유, 경로 압축의 동작 방식을 질문했다.
 *
 * [최종적으로 이해한 해결 방법]
 * parent 배열로 각자의 부모를 관리하고, find(x)는 재귀적으로 루트를 찾으며 경로 압축(parent[x] = find(parent[x]))으로
 * 트리를 납작하게 만든다. union(a, b)는 두 루트가 다르면 한쪽 루트를 다른 쪽 루트 밑에 붙여 같은 무리로 합친다.
 * 마지막에 1~N 중 find(i) == i(자기 자신이 루트)인 개수를 세면 고립된 사람까지 포함한 전체 무리 개수가 된다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_7465_창용마을무리의개수 {

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); //만약 root값 아니면 부모를 호출
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA; // b의 값을 a(부모)로 바꿈
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= test_case; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int people = Integer.parseInt(st.nextToken());
            int relation = Integer.parseInt(st.nextToken());

            parent = new int[people + 1];
            for (int i = 1; i <= people; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < relation; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            int result = 0;
            for (int i = 1; i <= people; i++) {
                if (find(i) == i) {
                    result++;
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }
}