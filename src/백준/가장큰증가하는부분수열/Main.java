package 백준.가장큰증가하는부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] array;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp = new int[N];

        for(int i = 0; i < N; i++) {
            dp[i] = array[i];
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(array[i] > array[j]) {
                   dp[i] = Math.max(dp[i], dp[j] + array[i]);
                }
            }
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
