package 알고리즘문제해결전략1.chapter6.동적계획법.외발뛰기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[][] map;
    private static boolean[][] dp;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            map[i] = arr;
        }

        dp = new boolean[n][n];

        System.out.println(checkDFS(0,0));
        System.out.println(checkDP(0,0));
    }

    private static boolean checkDFS(int cury, int curx) { // 완전 탐색 방식은 시간복잡도가 2^n이 됩니다.

        // 벗어나는 범위 기저사례로 제거
        if(cury >= n || curx >= n) return false;
        if(cury == n - 1 && curx == n - 1) return true;

        int size = map[cury][curx];
        return checkDFS(cury + size, curx) || checkDFS(cury, curx + size);
    }

    private static boolean checkDP(int cury, int curx) {

        if(cury >= n || curx >= n) return false;
        if(cury == n - 1 && curx == n -1) return true;

        if(dp[cury][curx]) { // 이 방향으로는 갈 수 있습니다.
           return true;
        }
        int size = map[cury][curx];
        return dp[cury][curx] = checkDP(cury + size, curx) || checkDP(cury, curx + size);
    }

}
