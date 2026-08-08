package week03.곽정민;

import java.io.IOException;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1860_진기의최고급붕어빵 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int selected = Integer.parseInt(st.nextToken());
            int makeTime = Integer.parseInt(st.nextToken());
            int makeCount = Integer.parseInt(st.nextToken());

            int[] arrive = new int[selected];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < selected; i++) {
                arrive[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arrive);

            boolean possible = true;
            int consume = 0;
            for (int i = 0; i < arrive.length; i++) {
                int made = makeCount * (arrive[i] / makeTime);
                if (made <= consume) { // 재고가 0 이하 → 이 손님 못 받음
                    possible = false;
                    break;
                }
                consume++;
            }

            System.out.println("#" + tc + " " + (possible ? "Possible" : "Impossible"));
        }
    }
}
