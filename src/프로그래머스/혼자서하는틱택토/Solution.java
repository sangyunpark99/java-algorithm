package 프로그래머스.혼자서하는틱택토;

class Solution {
    public int solution(String[] board) {
        char[][] b = new char[3][3];
        for (int i = 0; i < 3; i++) {
            b[i] = board[i].toCharArray();
        }

        int countO = 0, countX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b[i][j] == 'O') countO++;
                else if (b[i][j] == 'X') countX++;
            }
        }

        if (!(countO == countX || countO == countX + 1)) {
            return 0;
        }

        int winO = countWins(b, 'O');
        int winX = countWins(b, 'X');

        if (winO > 0) { // O가 빙고인 경우
            if(winX > 0) return 0; // X도 빙고가 있으면 안된다
            return (countO == countX + 1) ? 1 : 0; // O의 갯수가 한개 더 많아야 한다.
        }

        if (winX > 0) { // X가 빙고인 경우
            if(winO > 0) return 0; // O도 빙고가 있으면 안된다.
            return (countO == countX) ? 1 : 0; // O의 갯수랑 X의 갯수가 같아야 한다.
        }

        return 1;
    }

    private int countWins(char[][] b, char c) {
        int wins = 0;

        for (int i = 0; i < 3; i++) {
            if (b[i][0] == c && b[i][1] == c && b[i][2] == c) {
                wins++;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (b[0][j] == c && b[1][j] == c && b[2][j] == c) {
                wins++;
            }
        }

        if (b[0][0] == c && b[1][1] == c && b[2][2] == c) wins++;
        if (b[0][2] == c && b[1][1] == c && b[2][0] == c) wins++;

        return wins;
    }
}
