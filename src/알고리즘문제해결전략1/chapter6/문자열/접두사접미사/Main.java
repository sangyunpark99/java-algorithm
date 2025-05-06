package 알고리즘문제해결전략1.chapter6.문자열.접두사접미사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String value = br.readLine();

        prefixSuffixLength(value);
    }

    private static void prefixSuffixLength(String s) {
        int n = s.length();
        int[] lps = new int[n];

        for(int i = 1, j = 0; i < n; i++) { // 2개부터 시작
            while(j  > 0 && s.charAt(i) != s.charAt(j)) { // 일치하지 않는 경우
                j = lps[j-1]; // 이전 누적된 갯수와 동일
            }

            if(s.charAt(i) == s.charAt(j)) { // 일치하는 경우
                lps[i] = ++j;
            }
        }

        List<Integer> result = new ArrayList<>();
        int len = lps[n-1];
        while(len > 0) {
            result.add(len);
            len = lps[len - 1];
        }

        Collections.reverse(result);
        for(int lenVal: result) {
            System.out.println(lenVal + " ");
        }
    }
}
