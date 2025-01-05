package 프로그래머스.lv0모음제거;

class Solution {

    public static String[] value = {"a", "e", "i", "o", "u"};

    public String solution(String my_string) {
        String answer = my_string;

        for (String a : value) {
            answer = answer.replace(a, "");
        }

        return answer;
    }
}
