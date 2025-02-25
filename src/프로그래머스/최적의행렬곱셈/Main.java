package 프로그래머스.최적의행렬곱셈;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println(new Main().solution(new int[][] {
                {5,3},
                {3,10},
                {10,6}
        }));

    }

    private int[][] dp;
    private int[][] matrix;

    // 어떻게 하면 중복 계산을 피하면서 최적의 곱셈 순서를 찾을 수 있을까?

    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;

        matrix = matrix_sizes;
        dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return go(0, n - 1);
    }

    private int go(int start, int end) {
        if(start == end) {
            return 0;
        }

        if(dp[start][end] != -1) return dp[start][end];

        int minValue = Integer.MAX_VALUE;

        for(int k = start; k < end; k++) {
            int value = go(start, k) + go(k+1, end)
                    + matrix[start][0] * matrix[k][1] * matrix[end][1];

            minValue = Math.min(minValue, value);
        }

        return dp[start][end] = minValue;
    }
}