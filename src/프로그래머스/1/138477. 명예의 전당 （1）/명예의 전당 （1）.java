import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Integer> lowScores = new ArrayList<>();
        
        for(int value: score) {
            pq.offer(value);
            
            if(pq.size() > k) {
                pq.poll(); // 점수가 낮은 값이 들어와도 먼저 빠지게 된다.
            }
            
            lowScores.add(pq.peek()); // 가장 낮은 점수 추가
        }
        
        return lowScores.stream().mapToInt(Integer::intValue).toArray();
    }
}