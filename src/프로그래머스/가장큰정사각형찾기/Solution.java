package 프로그래머스.가장큰정사각형찾기;

import java.util.*;

class Solution {

    public int solution(int [][]board) {

        int answer = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 1 && i > 0 && j > 0) {
                    board[i][j] = Math.min(board[i-1][j], Math.min(board[i][j-1], board[i-1][j-1])) + 1;
                }
                answer = Math.max(answer, board[i][j]);
            }
        }

        return answer * answer;
    }
}