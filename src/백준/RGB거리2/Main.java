package 백준.RGB거리2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int N;
    private static int[][] cost;
    private static int[][] dp;
    private static final int INF = 1_000_001; // 안전한 최대값 설정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp = new int[N][3];

        int answer = INF;


        for (int firstColor = 0; firstColor < 3; firstColor++) {

            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], -1);
            }

            answer = Math.min(answer, dfs(1, firstColor, firstColor) + cost[0][firstColor]);
        }

        System.out.println(answer);
    }

    private static int dfs(int cur, int color, int firstColor) {
        if (cur == N) {
            return 0;
        }

        if (dp[cur][color] != -1) return dp[cur][color];

        int result = INF;
        for (int next = 0; next < 3; next++) {
            if(cur == N - 1 && firstColor == next) continue;
            if (color != next) { // 연속해서 같은 색을 칠할 수 없음
                result = Math.min(result, cost[cur][next] + dfs(cur + 1, next, firstColor));
            }
        }

        return dp[cur][color] = result;
    }
}
