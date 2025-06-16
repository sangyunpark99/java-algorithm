package 프로그래머스._3n타일링;

import java.util.*;

class Solution {
    public int solution(int n) {
        long [] dp = new long [n+1];
        dp[2] = 3;

        for(int i = 4; i <= n; i += 2){
            dp[i] = dp[i-2] * dp[2] + 2;

            for(int j = 2; j <= i-4; j += 2) {
                dp[i] += dp[j] * 2;
            }

            dp[i] = dp[i] % 1000000007;
        }

        return (int) dp[n];
    }
}