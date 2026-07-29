/*
 * [풀이 도움 여부]
 * 구글링을 통해 도움을 받아 해결
 *
 * [나의 생각 과정]
 * 1. 문제에서 총점이 동일한 경우는 없다고 명시되어 있어 동점 처리는 고려하지 않아도 된다고 판단했다.
 * 2. 각 학생의 가중 평균 점수를 계산한 뒤 내림차순으로 정렬하고, 전체 인원을 10등분하여 등급을 매기는 방식으로 접근했다.
 * 3. 학생 정보를 index, score, grade로 묶어 Student 클래스로 관리할지, 배열/변수만으로 처리할지 고민했다.
 *
 * [막힌 부분]
 * 알고리즘 자체보다는 Java 문법에서 막혔다. 내림차순 정렬을 위한 람다(Comparator) 사용법과 배열 문법 구조를 정확히 몰랐다.
 *
 * [참고한 내용]
 * 구글링을 통해 Arrays.sort에서 람다식을 이용해 정렬 기준을 지정하는 방법과 배열 선언 및 사용 문법을 참고했다.
 *
 * [최종적으로 이해한 해결 방법]
 * Arrays.sort(students, (a, b) -> Double.compare(b.score, a.score))처럼 람다를 Comparator로 넘겨
 * 최종 점수 기준 내림차순 정렬을 구현할 수 있다는 것을 이해했다.
 */

import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;

public class SWEA_1983_조교의_성적_매기기 {

    public static class Student {
        private int index;
        private double score;
        private char grade;

        Student(int index, double score) {
            this.index = index;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int StudentNum = sc.nextInt();
            int targetStudent = sc.nextInt();

            Student[] students = new Student[StudentNum];
            for (int i = 0; i < StudentNum; i++) {
                int[] scores = new int[3];

                for (int j = 0; j < 3; j++) {
                    scores[j] = sc.nextInt(); // 학생의 점수 3분류를 입력받음
                }
                double final_score = scores[0] * 0.35 + scores[1] * 0.45 + scores[2] * 0.2; // 최종점수 계산
                Student student = new Student(i, final_score);
                students[i] = student;
            }
            Arrays.sort(students, (a, b) -> Double.compare(b.score, a.score)); // 최종점수 기준 내림차순 정렬

            String[] grades = { "A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0" };
            int group = StudentNum / 10;

            for (int i = 0; i < StudentNum; i++) {
                if (students[i].index == targetStudent - 1) {
                    System.out.println("#" + test_case + " " + grades[i / group]);
                    break;
                }
            }

        }

    }
}
