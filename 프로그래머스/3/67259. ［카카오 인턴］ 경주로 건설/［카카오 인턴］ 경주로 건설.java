import java.util.*;

class Solution {
    
    // 가중치가 다른 최소 비용 
    
    private int[][][] cost;
    private int[] dy = new int[] {-1,0,1,0};
    private int[] dx = new int[] {0,1,0,-1};
    private int n;
    private int INF = 1_000_000_000;
    
    private static class Node {
        int y;
        int x;
        int cost;
        int dir;
        
        public Node(int y, int x, int cost, int dir) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.dir = dir;
        }
    }
    
    public int solution(int[][] board) {
        int answer = INF;
        
        n = board.length;
        
        cost = new int[n][n][4];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(cost[i][j], INF);
            }
        }
        
        // 0(상), 1(오), 2(아), 3(왼)
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        
        if(board[0][1] == 0) {
            cost[0][1][1] = 100;
            pq.offer(new Node(0,1,100,1));
        }
        
        if(board[1][0] == 0) {
            cost[1][0][2] = 100;
            pq.offer(new Node(1,0,100,2));
        }
        
        while(!pq.isEmpty()) {
           
            Node cur = pq.poll();
            if(cost[cur.y][cur.x][cur.dir] < cur.cost) continue; // 느긋한 삭제
            
            for(int i = 0; i < 4; i++) { // 방향 
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                
                if(ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                if(board[ny][nx] == 1) continue;
                
                int addCost = cur.dir == i ? 100 : 600;
                int nCost = cur.cost + addCost;
                
                if(cost[ny][nx][i] > nCost) { // 더 작은 비용이 드는 경로인 경우만
                    cost[ny][nx][i] = nCost;
                    pq.offer(new Node(ny, nx, nCost, i));
                }
            }
        }
        
        for(int i = 0; i < 4; i++) {
            answer = Math.min(answer, cost[n - 1][n - 1][i]);
        }
        
        return answer;
    }
}