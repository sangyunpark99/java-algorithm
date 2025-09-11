import java.util.*;

class Solution {
    
    private List<List<Integer>> win = new ArrayList<>(); // 기준을 이긴 사람
    private List<List<Integer>> lose = new ArrayList<>(); // 기준 한테 진 사람
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        for(int i = 0; i <= n; i++) {
            win.add(new ArrayList<>());
            lose.add(new ArrayList<>());
        }
        
        for(int i = 0; i < results.length; i++) {
            int[] result = results[i];
            int a = result[0]; // 이긴 사람
            int b = result[1]; // 진 사람
            
            win.get(b).add(a); // a가 b를 이김
            lose.get(a).add(b); // b가 a한테 짐
        }
        
        for(int i = 1; i <= n; i++) {
            if(cnt(i, win, new boolean[n + 1]) + cnt(i, lose, new boolean[n + 1]) == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private int cnt(int num, List<List<Integer>> graph , boolean[] visited) {
        
        int total = 0;
        
        if(graph.get(num).size() == 0) {
            return 0;
        }        
        
        for(int i = 0; i < graph.get(num).size(); i++) {
            int parent = graph.get(num).get(i);
            if(!visited[parent]) {
                total += cnt(parent, graph, visited) + 1;
                visited[parent] = true;
            }
        }
        
        return total;
    }
}