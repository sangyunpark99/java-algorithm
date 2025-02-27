package 프로그래머스.이차원동전뒤집기;
import java.util.*;
public class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;

        int n = beginning.length;
        int m = beginning[0].length;
        int minFlips = Integer.MAX_VALUE;

        for(int mask = 0; mask < (1 << n); mask++) {
            int[][] board = new int[n][m];

            for(int i = 0; i < n; i++) {
                board[i] = beginning[i].clone();
            }

            int flipCnt = 0;

            // 행 뒤집기
            for(int i = 0; i < n; i++) {
                if((mask & (1 << i)) != 0) { // 행
                    flipCnt++;
                    for(int j = 0; j < m; j++) { // 뒤집기
                        board[i][j] ^= 1;
                    }
                }
            }

            for(int j = 0; j < m; j++) { // 열
                int colDiff = 0;
                for(int i = 0; i < n; i++) {
                    if(board[i][j] != target[i][j]) {
                        colDiff++;
                    }
                }

                if(colDiff == n) {
                    flipCnt++;
                } else if(colDiff > 0) {
                    flipCnt = Integer.MAX_VALUE;
                    break;
                }
            }

            minFlips = Math.min(minFlips, flipCnt);
        }

        return (minFlips == Integer.MAX_VALUE) ? -1 : minFlips;
    }
}
