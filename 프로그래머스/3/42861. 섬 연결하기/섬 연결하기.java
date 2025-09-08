import java.util.*;

class Solution {
    
    List<List<int[]>> graph = new ArrayList<>();
    private boolean[] visited;
    private int answer = 0;
    
    public int solution(int n, int[][] costs) {
        
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        visited = new boolean[n];
        
        for(int i = 0; i < costs.length; i++) {
            int[] cost = costs[i];
            int start = cost[0];
            int end = cost[1];
            int weight = cost[2];
            graph.get(start).add(new int[]{end, weight});
            graph.get(end).add(new int[]{start, weight});
        }
        
        findPath();
        
        return answer;
    }
    
    private void findPath() { // 제일 값이 작은 노드의 간선만 방문한다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> a[1] - b[1]);
        pq.offer(new int[]{0,0}); // [number, weight]
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll(); // 제일 가중치가 낮은 간선만 뽑는다.
            int curNumber = cur[0];
            int curWeight = cur[1];
            
            if(visited[curNumber]) { // 방문
                continue;
            }
            
            visited[curNumber] = true;
            answer += curWeight;
            
            for(int i = 0; i < graph.get(curNumber).size(); i++) {
                int[] next = graph.get(curNumber).get(i);
                int nextNumber = next[0];
                int nextWeight = next[1];
                if(!visited[nextNumber]) {
                    pq.offer(new int[]{nextNumber, nextWeight});
                }
            }
        }
    }
}