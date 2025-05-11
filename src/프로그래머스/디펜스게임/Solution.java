package 프로그래머스.디펜스게임;

import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        int gap = Integer.MIN_VALUE;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < enemy.length; i++) { // O(n)
            int value = enemy[i];
            pq.offer(value);
            if(n - value < 0) {
                if(k == 0) {
                    return i;
                }else {
                    k--;
                    n += pq.poll();
                    n -= value;
                }
            }else {
                n -= value;
            }
        }
        return answer == 0 ? enemy.length : answer;
    }
}