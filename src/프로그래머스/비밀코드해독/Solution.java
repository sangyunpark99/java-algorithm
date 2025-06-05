package 프로그래머스.비밀코드해독;

import java.util.*;

class Solution {

    private int[][] Q;
    private int[] Answer;
    private int answer = 0;

    public int solution(int n, int[][] q, int[] ans) {

        Q = new int[q.length][q[0].length];
        Answer = new int[ans.length];

        for(int i = 0; i < q.length; i++) {
            Q[i] = q[i].clone();
        }

        Answer = ans.clone();

        combi(new ArrayList<>(), 0, n, 0);

        return answer;
    }

    private void combi(List<Integer> values, int depth, int n, int start) {

        if(depth == 5) {

            if(check(values)) {
                answer++;
            }
            return;
        }

        for(int i = start + 1; i <= n; i++) {
            values.add(i);
            combi(values, depth + 1, n, i);
            values.remove(values.size() - 1);
        }
    }

    private boolean check(List<Integer> values) {

        for(int i = 0; i < Q.length; i++) {
            int value = 0;
            for(int j = 0; j < Q[0].length; j++) {
                if(values.contains(Q[i][j])) {
                    value++;
                }
            }

            if(Answer[i] != value) {
                return false;
            }
        }

        return true;
    }
}
