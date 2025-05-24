package 프로그래머스.퍼즐게임챌린지;

import java.util.*;

class Solution {

    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;

        long left = 1;
        long right = (long) Arrays.stream(diffs).max().getAsInt();

        while(left <= right) {
            long mid = (left + right) / 2;
            if(check(mid, diffs, times, limit)) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return (int) left;
    }

    public boolean check(long level, int[] diffs, int[] times, long limit) {
        long total = 0;
        for(int i = 0; i < diffs.length; i++) {
            long diff = diffs[i];

            if(diff <= level) {
                total += times[i];
            }else {
                if(i == 0) return false;
                long fail = diff - level;
                total += fail * (times[i] + times[i-1]) + times[i];
            }
        }
        return limit >= total;
    }
}
