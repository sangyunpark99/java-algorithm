import java.util.*;

class Solution {
    
    private List<List<Integer>> list = new ArrayList<>();
    private boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        
        int answer = 0;
        
        for(int i = 0; i < n; i++) { // O(n) = 200
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < n; i++) { // O(n^2) = 40000
            for(int j = 0; j < n; j++) {
                if(j == i) {
                    continue;
                }
                if(computers[i][j] == 1) {
                    list.get(i).add(j);   
                }
            }
        }
    
        visited = new boolean[n];
        for(int i = 0; i < n; i++) { // O(n) = 200
            if(!visited[i]) {
                visited[i] = true;
                System.out.println("i번째 노드 방문 : " + i);
                dfs(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dfs(int curNode) {
        for(int nextNode : list.get(curNode)) {
            if(!visited[nextNode]) {
                visited[nextNode] = true;
                dfs(nextNode);
            }
        }
    }
}