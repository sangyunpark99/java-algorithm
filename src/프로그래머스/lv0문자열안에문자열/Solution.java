package 프로그래머스.lv0문자열안에문자열;

public class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;

        if (str1.contains(str2)) {
            return 1;
        }

        return 2;
    }
}
