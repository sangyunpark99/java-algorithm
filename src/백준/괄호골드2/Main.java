package 백준.괄호골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static String[] dp = new String[10001];
    private static Map<Character, Character> map = new HashMap<>();

    private static boolean check(String before, String after) {
        if ((before == null || before.isEmpty()) && (after == null || after.isEmpty())) return false;
        if (before == null || before.isEmpty()) return true;
        if (before.length() == after.length()) return before.compareTo(after) > 0; // 길이가 같은 경우엔 값이
        return after.length() < before.length();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        Character[] a = {'(',')','{','}','[',']'};
        for(int i = 0; i < 6; i++) {
            map.put((char)('1' + i), a[i]);
        }

        dp[1] = "12";
        dp[2] = "34";
        dp[3] = "56";

        for(int i = 0; i <= 1000; i++) { // N은 최대 1000

            for(int j = 1; j < i; j++) { // 더하기
                if(check(dp[i], dp[j] + dp[i-j])) {
                    dp[i] = dp[j] + dp[i - j];
                }
            }

            // 곱하기
            if(i % 2 == 0 && check(dp[i], "1" + dp[i / 2] + "2")) { // 2의 배수
                dp[i] = "1" + dp[i / 2] + "2";
            }
            if (i % 3 == 0 && check(dp[i], "3" + dp[i / 3] + "4")) { // 3의 배수
                dp[i] = "3" + dp[i / 3] + "4";
            }
            if (i % 5 == 0 && check(dp[i], "5" + dp[i / 5] + "6")) { // 5의 배수
                dp[i] = "5" + dp[i / 5] + "6";
            }
        }

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            for(char ch: dp[n].toCharArray()) {
                System.out.print(map.get(ch));
            }
            System.out.println();
        }
    }
}
