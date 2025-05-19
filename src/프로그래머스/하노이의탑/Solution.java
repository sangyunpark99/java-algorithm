package 프로그래머스.하노이의탑;

import java.util.*;

class Solution {

    List<int[]> answer = new ArrayList<>();

    public int[][] solution(int n) {
        hanoi(n,1,3,2);
        return answer.toArray(new int[answer.size()][]);
    }

    private void hanoi(int n, int from, int to, int d) {
        if(n == 0) return;
        hanoi(n - 1, from, d, to); // 작은 원판 기둥 이
        answer.add(new int[]{from, to});
        hanoi(n - 1, d, to, from); // 큰 원판
    }
}
