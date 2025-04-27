package 프로그래머스.단어퍼즐;

import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        int len = t.length();
        int[] dp = new int[len + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for(int i = 0; i < len; i++) {
            if(dp[i] == Integer.MAX_VALUE) continue;
            for(String s: strs) {
                if(i + s.length() <= len && t.substring(i, i + s.length()).equals(s)) {
                    dp[i + s.length()] = Math.min(dp[i + s.length()], dp[i] + 1);
                }
            }
        }

        return dp[len] == Integer.MAX_VALUE ? -1 : dp[len];
    }
}
