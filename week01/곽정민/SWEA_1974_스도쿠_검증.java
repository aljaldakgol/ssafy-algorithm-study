/*
 * 문제: SWEA 1974 [S/W 문제해결 기본] 1일차 - 스도쿠 검증
 * 알고리즘: 완전탐색 (행/열/3x3 구역 각각 검증)
 *
 * [풀이 도움 여부]
 * Checkrow/Checkcol/Checkbox 등 검증 로직 구현에 AI(Claude Code)의 도움을 받아 해결
 *
 * [나의 생각 과정]
 * 1. 스도쿠가 유효하려면 모든 행, 모든 열, 모든 3x3 구역이 각각 1~9를 중복 없이 포함해야 한다고 생각했다.
 * 2. 행/열/구역 검사를 각각의 메서드로 나누고, 모두 만족해야 최종적으로 유효한 스도쿠라고 설계했다.
 *
 * [막힌 부분]
 * 3x3 구역은 인덱스를 어떻게 조합해서 값을 모아야 할지, 
 * 그리고 "1~9가 중복 없이 있는지"를 실제로 검사하는 코드를 어떻게 짜야 할지에서 시간이 좀 소비됐다
 *
 * [참고한 내용]
 *  없음
 *
 * [최종적으로 이해한 해결 방법]
 * boolean[10] 배열로 1~9 중 이미 나온 숫자를 표시해가며 범위 밖 값이나 중복이 있으면 false를
 * 반환하는 isValidGroup 헬퍼를 만들고, 행은 sudoku[i]를 그대로, 열은 열 번호(j)를 고정한 채
 * 행 번호(i)를 돌며 모은 값을, 3x3 구역은 blockRow/blockCol을 3칸씩 옮겨가며 모은 9칸의 값을
 * 각각 이 헬퍼에 넘겨 검사하도록 구현했다. 세 검사를 모두(&&) 통과해야 유효한 스도쿠로 판단한다.
 */

import java.util.Scanner;
import java.io.IOException;

public class SWEA_1974_스도쿠_검증 {

    // 9개의 값이 1~9를 중복 없이 정확히 하나씩 포함하는지 검사 (행/열/3x3 구역 공통으로 재사용)
    static boolean isValidGroup(int[] values) {
        boolean[] seen = new boolean[10];
        for (int value : values) {
            if (value < 1 || value > 9 || seen[value]) {
                return false;
            }
            seen[value] = true;
        }
        return true;
    }

    // 행(가로줄) 검사: sudoku[i]가 곧 i번째 행이므로 그대로 isValidGroup에 넘긴다
    static boolean Checkrow(int [][] sudoku){
        for (int i = 0; i < sudoku.length; ++i) {
            if (!isValidGroup(sudoku[i])) {
                return false;
            }
        }
        return true;
    }

    // 열(세로줄) 검사: 열 번호(j)를 고정해두고 행 번호(i)를 0~8로 바꾸며 값을 모아 하나의 열을 만든다
    static boolean Checkcol(int [][] sudoku){
        for (int j = 0; j < sudoku[0].length; ++j) {
            int[] column = new int[sudoku.length];
            for (int i = 0; i < sudoku.length; ++i) {
                column[i] = sudoku[i][j]; // j는 고정, i만 움직이며 세로로 값을 모음
            }
            if (!isValidGroup(column)) {
                return false;
            }
        }
        return true;
    }

    // 3x3 구역 검사: blockRow, blockCol을 (0,3,6)으로 옮겨가며 9개 구역의 좌상단 좌표를 정하고,
    // 그 안의 3x3(=9칸)을 훑어 하나의 구역 값 묶음을 만든다
    static boolean Checkbox(int [][] sudoku){
        for (int blockRow = 0; blockRow < 9; blockRow += 3) {
            for (int blockCol = 0; blockCol < 9; blockCol += 3) {
                int[] box = new int[9];
                int index = 0;
                for (int i = blockRow; i < blockRow + 3; ++i) {
                    for (int j = blockCol; j < blockCol + 3; ++j) {
                        box[index++] = sudoku[i][j];
                    }
                }
                if (!isValidGroup(box)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int[][] SudokuArr = new int [9][9];
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    SudokuArr[i][j] = sc.nextInt();         //배열에 스도쿠 입력
                }
            }

            // 행/열/구역 검사를 모두(&&) 통과해야 유효한 스도쿠 (하나라도 false면 그 뒤는 평가 안 함)
            boolean isValid = Checkrow(SudokuArr) && Checkcol(SudokuArr) && Checkbox(SudokuArr);
            System.out.println("#" + test_case + " " + (isValid ? 1 : 0));
        }

    }
}
