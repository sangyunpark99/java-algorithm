package 백준.진우의달여행;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    private static int[][] map;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M][3];


        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], 1_000_0001);
            }
        }

        // 0 은 왼쪽 대각선
        // 1 은 직진
        // 2 는 오른쪽 대각선
        for(int i = 0; i < M; i++) {
            dp[0][i][0] = map[0][i];
            dp[0][i][1] = map[0][i];
            dp[0][i][2] = map[0][i];
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < M; j++) {

                // 왼쪽 대각선 -> 직진, 오른족 대각선
                if(i > 0) {
                    dp[i][j][0] = Math.min(dp[i-1][j][1], dp[i-1][j][2]) + map[i][j];
                }

                // 직진 -> 왼쪽, 오른쪽
                if(i > 0 && j > 0) {
                    dp[i][j][1] = Math.min(dp[i-1][j-1][0], dp[i-1][j-1][2]) + map[i][j];
                }

                // 오른쪽 대각선 -> 왼쪽, 직진
                if(i > 0 && j < M - 1) {
                    dp[i][j][2] = Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + map[i][j];
                }

            }
        }

        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < M; i++) {
            answer = Math.min(answer,Math.min(dp[N-1][i][0], Math.min(dp[N-1][i][1], dp[N-1][i][2])));
        }

        System.out.println(answer);
    }
}
