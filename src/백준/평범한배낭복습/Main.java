package 백준.평범한배낭복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int K;
    public static int[] dp = new int[100001];

    public static void main(String[] args) throws IOException {
        // K 만큼 무게를 넣을 수 있는 가방에 최대의 가치 V를 내라

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        while (N > 0) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            for (int i = K; i >= w; i--) {
                dp[i] = Math.max(dp[i], dp[i - w] + v);
            }

            N--;
        }

        System.out.println(dp[K]);
    }
}