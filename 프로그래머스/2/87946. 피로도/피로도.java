import java.util.*;

class Solution {
    
    // 최소 피로도, 소모 피로도
    // 최대한 많이 탐험
    private int answer = Integer.MIN_VALUE;
    private boolean[] visited;
    private int hp;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        dfs(k, 0, dungeons);
        
        return answer;
    }
    
    private void dfs(int cur, int dungeonCnt, int[][] dungeons) {
        
        if(cur >= 0) {
            answer = Math.max(answer, dungeonCnt);
        }

        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i]) {
                int[] dungeon = dungeons[i];
                if(cur >= dungeon[0]) {
                    visited[i] = true;
                    dfs(cur - dungeon[1], dungeonCnt+1,dungeons);
                    visited[i] = false;
                } 
            }
        }
    }
}