package 프로그래머스.lv0.특정문자제거하기;

public class Solution {
    public String solution(String my_string, String letter) {
        String answer = "";

        for (char value : my_string.toCharArray()) {
            if (value == letter.charAt(0)) {
                continue;
            }

            answer += value;
        }

        return answer;
    }
}
