package 백준.자물쇠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int[] start, target;
    static int[][][][] dp;

    static int mod(int x) {
        return (x + 10) % 10;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // n 입력
        start = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray(); // start 배열 입력
        target = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray(); // target 배열 입력

        dp = new int[n+1][10][10][10]; // dp
        for (int[][][] d1 : dp) { // 최댓값으로 초기화
            for (int[][] d2 : d1) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, Integer.MAX_VALUE);
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        dp[0][0][0][0] = 0;
        q.offer(new int[]{0, 0, 0, 0});  // pos, a, b, c

        while (!q.isEmpty()) {
            int[] curState = q.poll();
            int pos = curState[0];
            int a = curState[1];
            int b = curState[2];
            int c = curState[3];
            int cost = dp[pos][a][b][c]; // 비용

            if (pos >= n) continue; // n 번째까지 위치한 경우

            int val = mod(start[pos] + a);
            if (val == target[pos]) { // 번호가 같은 경우
                if (dp[pos + 1][b][c][0] > cost) {
                    dp[pos + 1][b][c][0] = cost;
                    q.offer(new int[]{pos + 1, b, c, 0}); // 한 칸 옮김
                }
            }

            for (int k = 1; k <= 3; k++) {
                for (int num = 1; num <= 3; num++) {
                    for (int dir : new int[]{-1, 1}) { // 시계 or 반시계
                        int na = a;
                        int nb = b;
                        int nc = c;
                        if (num >= 1) na = mod(a + dir * k);
                        if (num >= 2) nb = mod(b + dir * k);
                        if (num == 3) nc = mod(c + dir * k);

                        if (dp[pos][na][nb][nc] > cost + 1) {
                            dp[pos][na][nb][nc] = cost + 1;
                            q.offer(new int[]{pos, na, nb, nc});
                        }
                    }
                }
            }
        }

        System.out.println(dp[n][0][0][0]);
    }
}