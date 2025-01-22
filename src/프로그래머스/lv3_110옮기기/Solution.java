package 프로그래머스.lv3_110옮기기;

import java.util.Stack;

public class Solution {
    private static final String VALUE = "110";

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) { // O(n) = 10000000
            String sValue = s[i];

            Stack<Character> stack = new Stack<>();
            int cnt = 0;

            for (char ch : sValue.toCharArray()) {
                stack.push(ch);
                if (stack.size() >= 3) {
                    int size = stack.size();
                    if (stack.get(size - 3) == '1' && stack.get(size - 2) == '1' && stack.get(size - 1) == '0') {
                        stack.pop();
                        stack.pop();
                        stack.pop();
                        cnt++;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) { // 나머지 요소 넣어준다.
                sb.insert(0, stack.pop());
            }

            int position = sb.length(); // 마지막 위치를 기본으로

            if (!sb.toString().contains("0")) {
                position = 0;
            } else {
                for (int j = position - 1; j >= 0; j--) { // 가장 뒤에 0인 경우
                    if (sb.charAt(j) == '0') {
                        position = j + 1;
                        break;
                    }
                }
            }

            StringBuilder result = new StringBuilder();
            result.append(sb.substring(0, position));
            result.append(VALUE.repeat(cnt));
            result.append(sb.substring(position));

            answer[i] = result.toString();
        }

        return answer;
    }
}
