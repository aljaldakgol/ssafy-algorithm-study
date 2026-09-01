import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_5643_키순서 {

	static Student[] students;

	static class Student {
		int num;
		List<Student> higher;
		List<Student> smaller;

		public Student(int num) {
			this.num = num;
			higher = new ArrayList<>();
			smaller = new ArrayList<>();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());

			students = new Student[N + 1];
			for (int i = 1; i < N + 1; i++) {
				students[i] = new Student(i);
			}

			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int smaller = Integer.parseInt(st.nextToken()), higher = Integer.parseInt(st.nextToken());

				students[higher].smaller.add(students[smaller]);
				students[smaller].higher.add(students[higher]);
			}

			int ans = 0;
			for (int i = 1; i < N + 1; i++) {
				Set<Integer> higherSet = new HashSet<>();
				Set<Integer> smallerSet = new HashSet<>();

				searchHigher(higherSet, i);
				searchSmaller(smallerSet, i);

				if (higherSet.size() + smallerSet.size() == N - 1)
					ans++;
			}

			sb.append(ans).append("\n");
		}

		System.out.println(sb);

	}

	public static void searchHigher(Set<Integer> set, int nextStudent) {
		for (Student s : students[nextStudent].higher) {
			if (set.contains(s.num))
				continue;

			set.add(s.num);
			searchHigher(set, s.num);
		}
	}

	public static void searchSmaller(Set<Integer> set, int nextStudent) {
		for (Student s : students[nextStudent].smaller) {
			if (set.contains(s.num))
				continue;

			set.add(s.num);
			searchSmaller(set, s.num);
		}
	}

}
