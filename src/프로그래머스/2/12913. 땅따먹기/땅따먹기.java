import java.util.*;

class Solution {
    
    private int[][] dp;
    
    int solution(int[][] land) {
        int answer = 0;
        // 행의 갯수 10만개
        // dfs + dp로 진행
        dp = new int[land.length][4]; // 행, 열 일때 최댓값
        
        for(int i = 0; i < land.length; i++) {
            Arrays.fill(dp[i], 0);
        }
        
        for(int i = 0; i < 4; i++) { // 첫번째 행 초기화
            dp[0][i] = land[0][i];
        }
        
        // top -> bottom 방식의 dp
        for(int i = 1; i < land.length; i++) { // 각행에 그 열을 선택한 경우
            // 0번째 값을 선택한 경우 -> 이전 1번,2번,3번을 선택한 경우 최댓값 + 현재값
            dp[i][0] = land[i][0] + Math.max(dp[i-1][1], Math.max(dp[i-1][2], dp[i-1][3]));
            // 1번째 값을 선택한 경우 -> 이전 0번,2번,3번을 선택한 경우 최댓값 + 현재값
            dp[i][1] = land[i][1] + Math.max(dp[i-1][0], Math.max(dp[i-1][2], dp[i-1][3]));
            // 2번째 값을 선택한 경우 -> 이전 0번,1번,3번을 선택한 경우 최댓값 + 현재값
            dp[i][2] = land[i][2] + Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][3]));
            // 3번째 값을 선택한 경우 -> 이전 0번,1번,2번을 선택한 경우 최댓값 + 현재값
            dp[i][3] = land[i][3] + Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
        }
        
        int row = land.length;
        
        // 최종 행에 저장된 여러 값 중 최댓값 찾기
        answer = Math.max(dp[row - 1][0], Math.max(dp[row - 1][1], Math.max(dp[row - 1][2], dp[row - 1][3])));
        
        return answer;
    }
}