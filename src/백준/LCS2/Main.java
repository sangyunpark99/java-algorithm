package 백준.LCS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();

        dp = new int[first.length() + 1][second.length() + 1];

        for(int i = 1; i <= first.length(); i++) {
            for(int j = 1; j <= second.length(); j++) {
                if(first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[first.length()][second.length()]);

        StringBuilder lcs = new StringBuilder();

        int i = first.length();
        int j = second.length();

        while( i > 0 && j > 0) {
            if(first.charAt(i - 1) == second.charAt(j - 1)) {
                lcs.append(first.charAt(i - 1));
                i--;
                j--;
            }else if(dp[i-1][j] > dp[i][j-1]) { // 더 큰 값을 추적
                i--;
            }else {
                j--;
            }
        }

        System.out.println(lcs.reverse());
    }
}
