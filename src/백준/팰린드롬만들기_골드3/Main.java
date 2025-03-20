package 백준.팰린드롬만들기_골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] arr;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            dp[i][i] = 0;
        }

        for(int gap = 0; gap < N; gap++) {
            int maxLen = arr.length - gap; //
            for(int i = 0; i < maxLen; i++) {
                int j = i + gap;
                if(j > 0 && i < N - 1 && arr[i] == arr[j]) { // 양 끝이 같은 경우
                    dp[i][j] = dp[i+1][j-1];
                }else if(j > 0 && i < N - 1){
                    dp[i][j] = Math.min(dp[i][j-1], dp[i+1][j]) + 1;
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}
