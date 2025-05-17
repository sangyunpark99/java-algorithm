package 알고리즘문제해결전략1.chapter6.동적계획법.합친LIS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[][] dp;
    private static int[] A,B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            A = Arrays.stream(st.nextToken().split(" ")).mapToInt(Integer::parseInt).toArray();
            B = Arrays.stream(st.nextToken().split(" ")).mapToInt(Integer::parseInt).toArray();

            dp = new int[n + 1][m + 1];
            for(int[] row : dp) Arrays.fill(row, -1);

            int result = dfs(-1, -1);
            System.out.println(result);
        }
    }

    private static int dfs(int indexA, int indexB) {

        if(dp[indexA][indexB] != -1) return dp[indexA][indexB];

        long a = indexA == -1 ? Long.MIN_VALUE: A[indexA];
        long b = indexB == -1 ? Long.MAX_VALUE: B[indexB];
        long max = Math.max(a,b);

        int maxLen = 0;

        for(int nextA = indexA + 1; nextA <= indexB; nextA++) {
            if(A[nextA] > max) {
                maxLen = Math.max(maxLen, dfs(nextA, indexB));
            }
        }

        return 1;
    }
}
