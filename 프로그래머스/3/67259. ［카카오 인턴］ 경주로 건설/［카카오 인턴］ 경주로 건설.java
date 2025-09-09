import java.util.*;

class Solution {
    
    private int[][][] cost;
    private int INF = 1_000_000_000;
    private int[] dy = new int[] {-1, 0, 1, 0};
    private int[] dx = new int[] {0, 1, 0, -1};
    private int n;
    
    public static class Node {
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
        cost = new int[board.length][board.length][4];
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                Arrays.fill(cost[i][j], INF);
            }
        }
        
        // y, x, cost, dir
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.cost, b.cost));
        
        if(board[0][1] == 0) { // 오른쪽으로 출발하는 경우
            cost[0][1][1] = 100;
            pq.offer(new Node(0, 1, 100, 1));
        }
        
        if(board[1][0] == 0) { // 아래로 출발하는 경우
            cost[1][0][2] = 100;
            pq.offer(new Node(1, 0, 100, 2));
        }
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.cost > cost[cur.y][cur.x][cur.dir]) continue;
            
            for(int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                
                if(ny <  0 || ny >= n || nx < 0 || nx >= n) continue;
                if(board[ny][nx] == 1) continue; // 벽인 경우 패스
                
                int addCost = i == cur.dir ? 100 : 600;
                int nCost = cur.cost + addCost;
                
                if(nCost < cost[ny][nx][i]) {
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