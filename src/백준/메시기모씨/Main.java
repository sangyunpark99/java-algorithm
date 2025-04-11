package 백준.메시기모씨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    // 아이디어 : 해당 번호가 어떠한 문자에 속해있는지 알아야 한다.
    // 사용 알고리즘 : dp
    // 구현 :

    private static int max = 1001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        m--;

        char[] s = "Messi Gimossi".toCharArray();

        long[] dp = new long[max];
        dp[0] = 0;
        dp[1] = 5;
        dp[2] = 13;

        for(int i = 3; i <= 50; i++) { // 1을 더해주는 이유는 공백 추가
            dp[i] = dp[i-1] + dp[i-2] + 1;
        }

        System.out.println(Arrays.toString(dp));

        for(int i = 50; i >= 2; i--) {
            if(m >= dp[i]) {
                m -= (dp[i] + 1); // 1을 더 빼주는 이유는 공백
            }
        }

        if(m == 5 || m == -1) { // 공백인 경우
            System.out.println("Messi Messi Gimossi");
        }else {
            System.out.println(s[m]);
        }
    }
}
