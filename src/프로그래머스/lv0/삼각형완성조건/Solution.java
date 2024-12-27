package 프로그래머스.lv0.삼각형완성조건;

import java.util.Arrays;

public class Solution {
    public int solution(int[] sides) {
        int answer = 2;

        Arrays.sort(sides); // nlogn

        int longLine = sides[sides.length - 1];

        if (longLine < sides[0] + sides[1]) {
            answer = 1;
        }

        return answer;
    }
}
