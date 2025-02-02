package 프로그래머스.최소직사각형;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;

        // 가장 긴 변을 가로 * 가장 짧은 변을 세로
        List<Integer> maxValue = new ArrayList<>();
        List<Integer> minValue = new ArrayList<>();

        for (int[] size : sizes) {
            maxValue.add(Math.max(size[0], size[1]));
            minValue.add(Math.min(size[0], size[1]));
        }

        Collections.sort(maxValue);
        Collections.sort(minValue);

        answer = maxValue.get(maxValue.size() - 1) * minValue.get(minValue.size() - 1);

        return answer;
    }
}