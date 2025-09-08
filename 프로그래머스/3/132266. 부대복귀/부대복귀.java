import java.util.*;

class Solution {
    
    private int[] visited;
    private List<List<Integer>> graph = new ArrayList<>();
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        visited = new int[n + 1];
        Arrays.fill(visited, -1); // O(100,000)
        
        for(int i = 0; i <= n; i++) { // O(100,000)
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < roads.length; i++) { // O(500,000)
            int[] road = roads[i];
            int start = road[0];
            int end = road[1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        
        bfs(destination); // O(V+E) : (100,000 + 500,000)
        
        int[] answer = new int[sources.length];
        
        for(int i = 0; i < sources.length; i++) { // O(500)
            int value = sources[i];
            answer[i] = visited[value];
        }
        
        return answer;
    }
    
    private void bfs(int destination) {
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(destination);
        visited[destination] = 0;
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            
            for(int i = 0; i < graph.get(cur).size(); i++) {
                int next = graph.get(cur).get(i);
                if(visited[next] == -1) {
                    visited[next] = visited[cur] + 1;
                    queue.offer(next);
                }
            }
        }
    }
}