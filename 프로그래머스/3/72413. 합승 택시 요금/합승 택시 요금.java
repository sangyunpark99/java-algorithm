import java.util.*;

class Solution {
    
    private List<List<int[]>> graph = new ArrayList<>();
    private int[] cost;
    private int[] aCost;
    private int[] bCost;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < fares.length; i++) {
            int[] fare = fares[i];
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            
            graph.get(c).add(new int[]{d,f});
            graph.get(d).add(new int[]{c,f});
        }
        
        cost = new int[n + 1];
        aCost = new int[n + 1];
        bCost = new int[n + 1];
        
        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(aCost, Integer.MAX_VALUE);
        Arrays.fill(bCost, Integer.MAX_VALUE);
        
        findShortestPath(s, cost);
        findShortestPath(a, aCost);
        findShortestPath(b, bCost);
        
        int result = Integer.MAX_VALUE;
        
        for(int i = 1; i <= n; i++) {
            result = Math.min(result, cost[i] + aCost[i] + bCost[i]);
        }
    
        return result;
    }
    
    private void findShortestPath(int start,int[] costArr) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[1] - b[1]; // 비용 오름차순
        });
        
        costArr[start] = 0;
        pq.offer(new int[]{start, 0});
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int number = cur[0];
            int cost = cur[1];
            
            for(int i = 0; i < graph.get(number).size(); i++) {
                int[] next = graph.get(number).get(i);
                int nextNumber = next[0];
                int toCost = next[1];
                
                if(costArr[nextNumber] > costArr[number] + toCost) {
                    costArr[nextNumber] = costArr[number] + toCost;
                    pq.offer(new int[]{nextNumber, costArr[nextNumber]});
                }
            }
        }
    }
}