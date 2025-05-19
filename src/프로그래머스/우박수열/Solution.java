package 프로그래머스.우박수열;

import java.util.*;

class Solution {

    private double[] result;
    private List<int[]> graph = new ArrayList<>();
    private double[] sum;

    public double[] solution(int k, int[][] ranges) {
        double[] answer = {};

        result = new double[ranges.length];

        graph.add(new int[]{0, k});
        int n = getN(k, 1);

        sum = new double[n + 1];

        for(int i = 1; i < n + 1; i++) {
            sum[i] = sum[i-1] + ((graph.get(i-1)[1] + graph.get(i)[1]) / 2.0);
        }


        for(int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];
            int end = n + ranges[i][1];

            if (start > end) { // 시작 지점이
                result[i] = -1.0;
            } else {
                result[i] = sum[end] - sum[start];
            }
        }

        return result;
    }

    private int getN(int number, int idx) {

        int total = 0;

        if(number <= 1) {
            return 0;
        }

        if(number % 2 == 0) {
            number /= 2;
        }else if(number % 2 == 1) {
            number = number * 3 + 1;
        }

        graph.add(new int[]{idx, number});
        total = 1 + getN(number, ++idx);

        return total;
    }
}
