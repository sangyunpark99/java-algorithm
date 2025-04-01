package 백준.극장좌석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static  int[] dp;
    private static int N,M;
    private static int[] vip;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        dp[0] = 1;
        if(N >= 1) {
            dp[1] = 1;
        }

        if(N >= 2) {
            dp[2] = 2;
        }

        vip = new int[M];

        for(int i = 0; i < M; i++) {
            vip[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int answer = 1;

        int bfValue = 0;
        for(int i = 0; i < vip.length; i++) {
            int value = vip[i];
            answer *= dp[value - bfValue - 1];
            bfValue = value;
        }

        answer *= dp[N - bfValue];

        System.out.println(answer);
    }
}
