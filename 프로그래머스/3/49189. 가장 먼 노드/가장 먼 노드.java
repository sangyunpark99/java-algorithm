import java.util.*;

class Solution {
    
    private boolean[] visited;
    private int maxDepth = Integer.MIN_VALUE;
    private HashMap<Integer, List<Integer>> map = new HashMap<>();
    private List<List<Integer>> graph = new ArrayList<>();
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        visited = new boolean[n + 1];
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edge.length; i++) {
            int[] value = edge[i];
            int first = value[0];
            int second = value[1];
            graph.get(first).add(second);
            graph.get(second).add(first);
        }
        
        bfs();
        return map.get(maxDepth).size();
    }
    
    private void bfs() { // O(V + E) = 70,000 
        visited[1] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1,0});
        
        while(!queue.isEmpty()) {
            int[] value = queue.poll();
            int number = value[0];
            int depth = value[1];
            maxDepth = Math.max(maxDepth, depth);
            if(!map.containsKey(depth)) {
                map.put(depth, new ArrayList<>());
            }
            map.get(depth).add(number);
            for(int i = 0; i < graph.get(number).size(); i++) {
                int next = graph.get(number).get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, depth + 1});
                }
            } 
        }
    }
}