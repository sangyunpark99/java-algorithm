package 프로그래머스.숫자타자대회;

import java.util.*;

class Solution {
    public int[][] cost = {
            { 1, 7, 6, 7, 5, 4, 5, 3, 2, 3 },
            { 7, 1, 2, 4, 2, 3, 5, 4, 5, 6 },
            { 6, 2, 1, 2, 3, 2, 3, 5, 4, 5 },
            { 7, 4, 2, 1, 5, 3, 2, 6, 5, 4 },
            { 5, 2, 3, 5, 1, 2, 4, 2, 3, 5 },
            { 4, 3, 2, 3, 2, 1, 2, 3, 2, 3 },
            { 5, 5, 3, 2, 4, 2, 1, 5, 3, 2 },
            { 3, 4, 5, 6, 2, 3, 5, 1, 2, 4 },
            { 2, 5, 4, 5, 3, 2, 3, 2, 1, 2 },
            { 3, 6, 5, 4, 5, 3, 2, 4, 2, 1 }
    };

    public int[][][] dp;
    public String arr;
    public int len;

    public int solve(int idx, int L, int R) {
        if (idx == len) {
            return 0;
        }
        if (dp[idx][L][R] != -1) return dp[idx][L][R];

        int num = arr.charAt(idx) - '0';
        int result = Integer.MAX_VALUE;

        if (num != R) result = Math.min(solve(idx+1, num, R) + cost[L][num], result);

        if (num != L) result = Math.min(solve(idx+1, L, num) + cost[R][num], result);
        return dp[idx][L][R] = result;
    }

    public int solution(String numbers) {
        arr = numbers;
        len = numbers.length();
        dp = new int[len + 1][10][10];
        for (int i = 0; i < len + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
        return solve(0, 4, 6);
    }
}
