import java.util.*;

class Solution {
    
    private int[] dp;
    
    public int solution(int sticker[]) {
        int answer = 0;

        dp = new int[sticker.length];
        
        if(sticker.length == 1) return sticker[0];
        if(sticker.length == 2) return Math.max(sticker[0], sticker[1]);
        
        // 0번 시작, 마지막 선택 x
        dp[0] = sticker[0];
        dp[1] = Math.max(dp[0], sticker[1]);
        
        for(int i = 2; i < sticker.length - 1; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }
        
        answer = Math.max(answer, dp[sticker.length - 2]);
        
        // 초기화
        dp = new int[sticker.length];
        
        // 1번 시작, 마지막 선택 o
        dp[1] = sticker[1];
        dp[2] = Math.max(dp[1], sticker[2]);
        
        for(int i = 3; i < sticker.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }
        
        answer = Math.max(answer, dp[dp.length - 1]);
        
        return answer;
    }
}