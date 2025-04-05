package 백준.계단수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][][] dp = new long[N + 1][10][1 << 10];

        for (int d = 1; d <= 9; d++) {
            dp[1][d][1 << d] = 1;
        }


        for (int i = 1; i < N; i++) {
            for (int d = 0; d <= 9; d++) {
                for (int mask = 0; mask < (1 << 10); mask++) {
                    long val = dp[i][d][mask];
                    if (val == 0) continue;

                    if (d > 0) {
                        int nextMask = mask | (1 << (d - 1));
                        dp[i + 1][d - 1][nextMask] = (dp[i + 1][d - 1][nextMask] + val) % MOD;
                    }

                    if (d < 9) {
                        int nextMask = mask | (1 << (d + 1));
                        dp[i + 1][d + 1][nextMask] = (dp[i + 1][d + 1][nextMask] + val) % MOD;
                    }
                }
            }
        }

        long answer = 0;
        int fullMask = (1 << 10) - 1; // 모든 자리를 사용한 경우
        for (int d = 0; d <= 9; d++) {
            answer = (answer + dp[N][d][fullMask]) % MOD;
        }

        System.out.println(answer);
    }
}