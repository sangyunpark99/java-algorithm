package 백준.앱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n];
        int[] cost = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int maxCost = 0;
        for (int i = 0; i < n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            maxCost += cost[i]; // 최대로 많이 드는 비용
        }

        int[] dp = new int[maxCost + 1];

        for (int i = 0; i < n; i++) {
            int curMemory = memory[i];
            int curCost = cost[i];
            for (int j = maxCost; j >= curCost; j--) {
                dp[j] = Math.max(dp[j], dp[j - curCost] + curMemory);
            }
        }

        int answer = 0;

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= m) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
