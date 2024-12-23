package 프로그래머스.파괴되지않은건물;

public class Solution {

    public int solution(int[][] board, int[][] skills) {
        int answer = 0;

        // board 저장

        // 250,000 * 1000 * 1000 (r1,c1) -> (r2,c2) (0,0) -> (1000,1000)
        // 브루트 포스 방식은 하면 안된다.

        // 굳이 일일히 더하고 빼지 않는 방법
        // 범위를 저장?

        // (0,0) -> (3,4)

        int[][] pSumBoard = new int[board.length + 1][board[0].length + 1];

        // 누적합 방식을 사용하라는데 어떻게 사용해야 할까?
        for (int i = 0; i < skills.length; i++) { // O(n) = 250,000
            int type = skills[i][0];
            int y1 = skills[i][1];
            int x1 = skills[i][2];
            int y2 = skills[i][3];
            int x2 = skills[i][4];
            int degree = skills[i][5];

            if (type == 1) { // 파괴
                pSumBoard[y1][x1] -= degree;
                pSumBoard[y1][x2 + 1] += degree;
                pSumBoard[y2 + 1][x1] += degree;
                pSumBoard[y2 + 1][x2 + 1] -= degree;
            } else if (type == 2) { // 회복
                pSumBoard[y1][x1] += degree;
                pSumBoard[y1][x2 + 1] -= degree;
                pSumBoard[y2 + 1][x1] -= degree;
                pSumBoard[y2 + 1][x2 + 1] += degree;
            }
        }

        // 오른쪽 누적합 O(n) = 1,000,000
        for (int i = 0; i < pSumBoard.length; i++) {
            for (int j = 0; j < pSumBoard[i].length; j++) {
                if (j >= 1) {
                    pSumBoard[i][j] = pSumBoard[i][j - 1] + pSumBoard[i][j];
                }
            }
        }

        // 아래쪽 누적합 누적합 O(n) = 1,000,000
        for (int i = 0; i < pSumBoard[0].length; i++) {
            for (int j = 0; j < pSumBoard.length; j++) {
                if (j >= 1) {
                    pSumBoard[j][i] = pSumBoard[j - 1][i] + pSumBoard[j][i];
                }
            }
        }

        answer = 0;

        // O(n) = 1,000,000
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + pSumBoard[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}