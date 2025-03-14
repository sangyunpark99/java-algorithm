package ACMCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상 정렬
// 어떤 일(노드)을 먼저 해야 다음 일을 할 수 있는 경우 사용합니다.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] buildTime = new int[N + 1];
            int[] dp = new int[N + 1];
            int[] isDegree = new int[N + 1]; // 위상
            List<Integer>[] graph= new ArrayList[N + 1];

            st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].add(y);
                isDegree[y]++;

            }

            int target = Integer.parseInt(br.readLine());

            Queue<Integer> queue = new LinkedList<>();

            // 위상 정렬은 시작점이 존재해야 합니다.
            for(int i = 1; i <= N; i++) {
                if(isDegree[i] == 0) {
                    queue.offer(i);
                    dp[i] = buildTime[i]; // 시작점 초기화
                }
            }

            while(!queue.isEmpty()) {
                int cur = queue.poll();

                for(int next: graph[cur]) {
                    isDegree[next]--;
                    dp[next] = Math.max(dp[next], dp[cur] + buildTime[next]);

                    if(isDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            sb.append(dp[target]).append("\n");
        }
        System.out.println(sb);
    }
}
