import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (a,b) -> { // 시간 별로 정리
            return a[0] - b[0];
        });
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        
        int n = jobs.length;
        int idx = 0; // idx 활용하기
        long time = 0;
        long ttl = 0;
        
        while(idx < n || !pq.isEmpty()) {
            while(idx < n && jobs[idx][0] <= time) {
                pq.offer(new int[]{jobs[idx][0], jobs[idx][1]});
                idx++;
            }
            
            if(pq.isEmpty()) { // 다음 작업 시간으로 이동
                time = Math.max(time, jobs[idx][0]);
                continue;
            }
            
            int[] cur = pq.poll();
            time += cur[1];
            ttl += (time - cur[0]);
        }
        
        return (int) (ttl / n);
    }
}