package 알고리즘문제해결전략1.chapter6.동적계획법.와일드카드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static boolean[][] dp;
    private static char[] W;
    private static char[] S;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            W = br.readLine().toCharArray();
            int turn = Integer.parseInt(br.readLine());
            List<String> answer = new ArrayList<>();

            for(int i = 0; i < turn; i++) { // O(n)
                S = br.readLine().toCharArray();
                dp = new boolean[101][101]; // 각 문자열은 최대 100개

                if (matchMemorized(0, 0)) {
                    answer.add(String.valueOf(S));
                }
            }

            Collections.sort(answer);
            for(String s : answer) {
                System.out.println(s);
            }
        }
    }

    private static boolean matchMemorized(int w, int s) { // dp 사용

        if(dp[w][s]) { // O(1)
            return true;
        }

        while(w < W.length && s < S.length && (W[w] == '?' || W[w] == S[s])) {
            return dp[w][s] = matchMemorized(w + 1, s + 1);
        }

        if(w == W.length) {
            return dp[w][s] = (s == S.length);
        }

        if(W[w] == '*') { // *를 만난 경우
            // *가 아무 글자도 매칭하지 않는경우 or 하나 이상 매칭하는 경우
            return dp[w][s] = matchMemorized(w + 1, s) || (s < S.length && matchMemorized(w, s + 1));
        }

        return dp[w][s];
    }

    private static boolean match(char[] pattern, char[] value) { // 완전 탐색

        int idx = 0;

        while(idx < pattern.length && idx < value.length && (pattern[idx] == '?' || value[idx] == pattern[idx])) {
            ++idx;
        }

        // 기저 사례
        if(idx == pattern.length) { // 이미 패턴을 전부 확인한 경우
            return idx == value.length; // 검색하고자 하는 문자의 길이와 동일해야 한다.
        }

        if(pattern[idx] == '*') {
            for(int skip = 0; skip + idx <= value.length; skip++) {
                if(match(Arrays.copyOfRange(pattern, idx + 1, pattern.length), Arrays.copyOfRange(value, skip + idx, value.length))) {
                    return true;
                }
            }
        }

        return false;
    }
}
