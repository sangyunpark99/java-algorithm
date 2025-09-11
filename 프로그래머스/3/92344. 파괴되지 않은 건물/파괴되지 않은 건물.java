import java.util.*;

class Solution {
    
    private int[][] diff;
    
    public int solution(int[][] board, int[][] skills) {
        int answer = 0;
        
        diff = new int[board.length + 1][board[0].length + 1]; // 일부로 하나 더 크게 만든다.
        
        for(int i = 0; i < skills.length; i++) { // O(n) = 250,000
            int[] skill = skills[i];
            int type = skill[0];
            int y1 = skill[1];
            int x1 = skill[2];
            int y2 = skill[3];
            int x2 = skill[4];
            int degree = skill[5];
            
            if(type == 1) { // 공격
                diff[y1][x1] -= degree;
                diff[y1][x2 + 1] += degree;
                diff[y2 + 1][x1] += degree;
                diff[y2 + 1][x2 + 1] -= degree;
            }else if(type == 2) { // 회복
                diff[y1][x1] += degree;
                diff[y1][x2 + 1] -= degree;
                diff[y2 + 1][x1] -= degree;
                diff[y2 + 1][x2 + 1] += degree;
            }
        }
        
        // 행 누적합
        for(int i = 0; i < board.length; i++) {
            for(int j = 1; j < board[i].length; j++) {
                diff[i][j] += diff[i][j-1];
            }
        }
        
        // 열 누적합
        for(int i = 0; i < board.length; i++) {
            for(int j = 1; j < board[i].length; j++) {
                diff[j][i] += diff[j-1][i];
            }
        }
        
        // 탐색
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] + diff[i][j] > 0) {
                    answer++;
                }
            }
        }
         
        return answer;
    }
}