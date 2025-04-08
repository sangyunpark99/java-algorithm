package 프로그래머스.보행자천국;

class Solution {
    int MOD = 20170805;

    int[] dy = {-1,0};
    int[] dx = {0,-1};

    public int solution(int m, int n, int[][] cityMap) {

        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        cityMap[0][0] = 2;

        for(int y = 0; y < m; y++) {
            for(int x = 0; x < n; x++) {
                if(cityMap[y][x] == 1) continue;
                for(int k = 0; k < 2; k++) {
                    int bx = x + dx[k];
                    int by = y + dy[k];

                    if(by < 0 || by >= m || bx < 0 || bx >= n) continue;

                    if(cityMap[by][bx] == 2) {
                        dp[y][x][k] += dp[by][bx][k];
                    }else {
                        dp[y][x][k] += dp[by][bx][0] + dp[by][bx][1];
                    }

                    dp[y][x][k] %= MOD;
                }
            }
        }

        return (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;
    }
}