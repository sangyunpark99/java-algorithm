package 백준.스도쿠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        board = new int[9][9];

        for(int i = 0; i < 9; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        solve(0,0);
    }

    public static boolean solve(int y, int x) {
        if(x == 9) {
            return solve(y + 1, 0);
        }

        if(y == 9) { // 행, 열, 3x3 전부 채워짐

            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            return true;
        }

        if(board[y][x] != 0) {
            return solve(y, x + 1);
        }

        for(int i = 1; i <= 9; i++) {
            if(isValid(y, x, i)) {
                board[y][x] = i;
                if(solve(y, x + 1)) return true;
                board[y][x] = 0; // 아닌 경우 원복
            }
        }

        return false;
    }

    public static boolean isValid(int y, int x, int num) {

        // 같은 행과열 체크
        for(int i = 0; i < 9; i++) {
            if(board[y][i] == num || board[i][x] == num) return false;
        }

        // 3x3 박스 체크
        int boxY = (y / 3) * 3; // 시작하는 칸
        int boxX = (x / 3) * 3; // 시작하는 칸

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[boxY + i][boxX + j] == num) return false;
            }
        }

        return true;
    }
}
