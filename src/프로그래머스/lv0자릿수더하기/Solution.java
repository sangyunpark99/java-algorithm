package 프로그래머스.lv0자릿수더하기;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        String[] number = String.valueOf(n).split("");

        for (String value : number) {
            answer += Integer.parseInt(value);
        }

        return answer;
    }
}
