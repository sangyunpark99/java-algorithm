package 백준.펠린드롬분할;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int[][] isPalindrome;
    public static int[] dp; // 최소 몇개의 팰린드롬으로 저장할 수 있는지

    public static void main(String[] args) throws IOException {
        // dp 어려운 문제는 전처리 과정이 필요하다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] value = br.readLine().split("");

        isPalindrome = new int[value.length + 1][value.length + 1];
        dp = new int[value.length];

        Arrays.fill(dp, Integer.MAX_VALUE); // 최솟값 갱신인 경우 최댓값으로 초기화

        for (int i = 0; i < value.length; i++) {
            isPalindrome[i][i] = 1;
        }

        for (int i = 0; i < value.length - 1; i++) {
            if (value[i].equals(value[i + 1])) {
                isPalindrome[i][i + 1] = 1;
            }
        }

        for (int i = 2; i < value.length; i++) { // 크기
            for (int j = 0; j < value.length - i; j++) {
                if (value[j].equals(value[j + i]) && isPalindrome[j + 1][j + i - 1] == 1) {
                    isPalindrome[j][j + i] = 1;
                }
            }
        }

        // O(N^2)
        for (int i = 0; i < value.length; i++) { // 1자리,2자리
            for (int j = 0; j <= i; j++) { // 최대 범위
                if (isPalindrome[j][i] == 1) { // 주어진 범위가 펠린 드롬인 경우
                    // j == 0 인경우
                    if (j == 0) { // 문자열 전체가 펠린드롬인 경우
                        dp[i] = 1; // 1개로 되어 있다.
                    } else { // 이전 까지 최소 분할 + 1
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1); // 이전 까지의 분할 갯수 + 1
                    }
                }
            }
        }

        System.out.println(dp[value.length - 1]);
    }
}