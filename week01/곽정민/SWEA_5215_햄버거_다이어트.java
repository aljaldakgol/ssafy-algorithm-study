/*
 * 문제: SWEA 5215 [S/W 문제해결 기본] 1일차 - 햄버거 다이어트
 * 알고리즘: 완전탐색 (DFS/재귀, 부분집합 탐색)
 *
 * [풀이 도움 여부]
 * dfs 재귀 함수 구현에 AI(Claude Code)의 도움을 받아 해결
 *
 * [나의 생각 과정]
 * 1. 재료 수 N이 최대 20으로 작아서, 각 재료를 "쓴다/안 쓴다"로 나누는 완전탐색(2^N)이 가능하다고 생각했다.
 * 2. 재료 정보를 담을 2차원 배열(ingredient[N][2])을 직접 설계하고, 입력을 받는 부분을 작성했다.
 *
 * [막힌 부분]
 * 재귀 함수의 base case와, 재료를 "쓴 경우/안 쓴 경우" 두 갈래로 나눠 재귀 호출한 뒤
 * 그 결과를 비교해 최댓값을 반환하는 구체적인 구현에서 막혔다.
 *
 * [참고한 내용]
 * Claude Code에게 dfs 재귀 함수의 구현을 요청해 참고했다.
 *
 * [최종적으로 이해한 해결 방법]
 * index가 ingredientCount에 도달하면(모든 재료 결정 완료) 칼로리 초과 여부로 유효성을 판단하고,
 * 그 전까지는 현재 재료를 "쓴 경우"와 "안 쓴 경우"를 각각 재귀 호출한 뒤 Math.max로 더 큰 값을
 * 반환하도록 구현했다. 이렇게 하면 2^N개의 leaf(각 leaf = 하나의 조합)가 만들어지고,
 * Math.max가 트리를 타고 올라가며 전체 조합 중 최댓값을 찾아준다는 것을 이해했다.
 */

import java.util.Scanner;
import java.io.IOException;

public class SWEA_5215_햄버거_다이어트 {

     static int dfs(int index, int totalCalorie, int totalScore, int[][] ingredient, int ingredientCount, int limitCalorie) {

        // base case: 모든 재료를 고려한 상태
        if (index == ingredientCount) {
            if (totalCalorie > limitCalorie) {
                return -1; // 칼로리 초과로 무효한 조합을 음수를 반환해 Math.max에서 자연스럽게 걸러지게 함
            }
            return totalScore;
        }

        // 현재 재료(index번째)를 "쓴다" 케이스
        int useCase = dfs(index + 1, totalCalorie + ingredient[index][1], totalScore + ingredient[index][0],
                ingredient, ingredientCount, limitCalorie);

        // 현재 재료(index번째)를 "안 쓴다" 케이스
        int skipCase = dfs(index + 1, totalCalorie, totalScore, ingredient, ingredientCount, limitCalorie);

        return Math.max(useCase, skipCase);
    }
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int Ingredient_Count = sc.nextInt(); // 재료 수
            int Limit_Calorie = sc.nextInt(); // 칼로리
            int[][] ingredient = new int[Ingredient_Count][2];
            for (int i = 0; i < Ingredient_Count; ++i) {

                int score = sc.nextInt(); // 민기의 맛에 대한 점수
                int calorie = sc.nextInt(); // 해당 재료의 칼로리
                ingredient[i][0] = score;
                ingredient[i][1] = calorie;

            }

            int maxScore = dfs(0, 0, 0, ingredient, Ingredient_Count, Limit_Calorie);
            System.out.println("#" + test_case + " " + maxScore);

        }

    }
}
