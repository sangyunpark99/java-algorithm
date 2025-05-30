package 프로그래머스.NQueen;

public class Solution {
    private int answer = 0;
    private boolean[] col;
    private boolean[] diag1;
    private boolean[] diag2;

    public int solution(int n) {
        col = new boolean[n];
        diag1 = new boolean[2 * n - 1]; // ↘ 대각선
        diag2 = new boolean[2 * n - 1]; // ↙ 대각선

        dfs(0, n);
        return answer;
    }

    private void dfs(int row, int n) {

        if(row == n) {
            answer++;
        }

        for(int c = 0; c < n; c++) {
            if(col[c] || diag1[row + c] || diag2[row - c + n - 1]) continue;
            col[c] = diag1[row + c] = diag2[row - c + n - 1] = true;
            dfs(row + 1, n);
            col[c] = diag1[row + c] = diag2[row - c + n - 1] = false;
        }
    }
}
