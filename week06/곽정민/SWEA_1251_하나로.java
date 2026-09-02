import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1251_하나로 {

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int test_case = Integer.parseInt(br.readLine().trim());
    for (int tc = 1; tc <= test_case; ++tc) {
      int N = Integer.parseInt(br.readLine().trim());
      int[][] coord = new int[N][2];

      for (int i = 0; i < 2; ++i) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; ++j) {
          coord[j][i] = Integer.parseInt(st.nextToken());
        }
      }
      double taxRate = Double.parseDouble(br.readLine());

      // dist[i] = 현재까지 만든 트리에서 섬 i까지 연결하는 데 드는 최소 비용(E * L^2)
      double[] dist = new double[N];
      boolean[] visited = new boolean[N];
      Arrays.fill(dist, Double.MAX_VALUE);
      dist[0] = 0;

      double totalCost = 0;

      for (int cnt = 0; cnt < N; ++cnt) {
        // 트리에 아직 안 들어간 섬들 중 dist가 가장 작은 섬을 선택
        int cur = -1;
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < N; ++i) {
          if (!visited[i] && dist[i] < minDist) {
            minDist = dist[i];
            cur = i;
          }
        }

        visited[cur] = true;
        totalCost += minDist;

        // 방금 편입한 섬(cur) 기준으로 나머지 섬들과의 연결 비용을 갱신
        for (int next = 0; next < N; ++next) {
          if (!visited[next]) {
            long dx = coord[cur][0] - coord[next][0];
            long dy = coord[cur][1] - coord[next][1];
            double cost = taxRate * (dx * dx + dy * dy);
            if (cost < dist[next]) {
              dist[next] = cost;
            }
          }
        }
      }

      sb.append('#').append(tc).append(' ').append(Math.round(totalCost)).append('\n');
    }

    System.out.print(sb);
  }
}
