package 프로그래머스.아날로그시계;

import java.util.*;

class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;

        int startSec = convertSec(h1, m1, s1);
        int endSec = convertSec(h2, m2, s2);

        answer = countAlarms(endSec) - countAlarms(startSec);
        answer += alarmNow(startSec) ? 1 : 0;

        return answer;
    }


    private int convertSec(int h, int m, int s) {
        return h * 3600 + 60 * m + s;
    }

    private int countAlarms(int seconds) {
        int minuteAlarms = seconds * 59 / 3600;
        int hourAlarms = seconds * 719 / 43200;
        int duplicatedAlarms = 43200 <= seconds ? 2 : 1;
        return minuteAlarms + hourAlarms - duplicatedAlarms;
    }

    private boolean alarmNow(int seconds) {
        return seconds * 59 / 3600 == 0 || seconds * 719 % 43200 == 0;
    }
}
