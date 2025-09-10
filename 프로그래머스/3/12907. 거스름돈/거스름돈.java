import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for(int i = 0; i < money.length; i++) {
            for(int sum = money[i]; sum <= n; sum++) {
                dp[sum] += dp[sum - money[i]];;
            }
        }
        
        
        
        return dp[n] % 1_000_000_007;
    }
}