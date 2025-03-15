package 백준.행렬곱셈순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[][] dp;
    private static int[][] matrix; // 각 행렬의 [행, 열] 정보를 저장
    private static int n;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n][n];
        matrix = new int[n][2];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0, n - 1));
    }

    private static int dfs(int start, int end) {
        if (start == end) {
            return 0;
        }

        if (dp[start][end] != -1) {
            return dp[start][end];
        }

        int minCost = Integer.MAX_VALUE;
        for (int k = start; k < end; k++) {
            minCost = Math.min(minCost, dfs(start, k) + dfs(k + 1, end) + matrix[start][0] * matrix[k][1] * matrix[end][1]);
        }
        return dp[start][end] = minCost;
    }
}
