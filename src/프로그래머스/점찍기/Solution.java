package 프로그래머스.점찍기;

import java.util.*;

class Solution {

    public long solution(int k, int d) {
        long answer = 0;

        for(int x = 0; k*x <= d; x++) {
            int y = (int) Math.sqrt((long) d * d - (long) k*x * k*x);
            answer += y / k + 1;
        }

        return answer;
    }
}