package week05.곽정민;

import java.io.*;
import java.util.*;
public class SWEA_1238_Contact {

  static int solve(int[] data, int start) {
    int max = 0;
    for (int num : data) {
      max = Math.max(max, num);
    }

    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i <= max; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < data.length; i += 2) {
      int from = data[i];
      int to = data[i + 1];
      graph.get(from).add(to);
    }

    boolean[] visited = new boolean[max + 1];
    visited[start] = true;

    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);

    int answer = start;

    while (!queue.isEmpty()) {
      int size = queue.size();
      int levelMax = -1;
      boolean received = false;

      for (int i = 0; i < size; i++) {
        int cur = queue.poll();
        for (int next : graph.get(cur)) {
          if (!visited[next]) {
            visited[next] = true;
            queue.add(next);
            received = true;
            levelMax = Math.max(levelMax, next);
          }
        }
      }

      if (received) {
        answer = levelMax;
      }
    }

    return answer;
  }

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = 10;
    for (int tc = 1; tc <= test_case; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int len = Integer.parseInt(st.nextToken());
      int start = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());
      int[] data = new int[len];
      for (int i = 0; i < len; i++) {
        data[i] = Integer.parseInt(st.nextToken());
      }

      int answer = solve(data, start);
      sb.append("#").append(tc).append(" ").append(answer).append("\n");
    }

    System.out.print(sb);
  }

}
