package 백준.계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] value = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            value[i] = Integer.parseInt(br.readLine());
        }

        if(n == 1) {
            System.out.println(value[1]);
            return;
        }

        if(n == 2) {
            System.out.println(value[1] + value[2]);
            return;
        }

        int[] dp = new int[n + 1];

        dp[1] = value[1]; // 한칸 이동
        dp[2] = dp[1] + value[2]; // 점프 이동

        for(int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + value[i - 1]) + value[i];
        }

        System.out.println(dp[n]);
    }
}