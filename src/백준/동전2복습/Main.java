package 백준.동전2복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int n;
    public static int k;
    public static int[] value;

    public static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[10001];

        Arrays.fill(dp, Integer.MAX_VALUE - 1);

        dp[0] = 0;

        for (int i = 0; i < n; i++) { // 1, 5, 12
            int temp = Integer.parseInt(br.readLine());
            for (int j = temp; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - temp] + 1);
            }
        }

        if (dp[k] == Integer.MAX_VALUE - 1) {
            System.out.println(-1);
            return;
        }

        System.out.println(dp[k]);
    }

}
