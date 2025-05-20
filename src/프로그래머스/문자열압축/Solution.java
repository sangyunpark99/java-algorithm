package 프로그래머스.문자열압축;

import java.util.*;

class Solution {
    // 문자열에서 같은 값이 연속해서 나타나는 것을 그 문자의 개수와 반복되는 값으로 표현
    // 가장 짧은 길이를 구하라

    public int solution(String s) {

        int answer = Integer.MAX_VALUE;

        // 단위를 기준으로 분기가 된다.
        for(int size = 1; size <= s.length() / 2; size++) {
            StringBuilder sb = new StringBuilder();
            String prev = s.substring(0, size);
            int count = 1;

            for(int i = size; i <= s.length(); i += size) {
                String curr = s.substring(i, Math.min(i + size, s.length()));
                if(prev.equals(curr)) {
                    count++;
                }else {
                    if(count > 1) sb.append(count);
                    sb.append(prev);
                    prev = curr;
                    count = 1;
                }
            }

            if(count > 1) sb.append(count);
            sb.append(prev);

            answer = Math.min(answer, sb.length());
        }

        return Math.min(answer, s.length());
    }
}