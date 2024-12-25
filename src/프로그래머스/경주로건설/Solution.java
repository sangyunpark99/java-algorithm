package 프로그래머스.경주로건설;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                }
        ));
    }

    int N;
    int[][][] visited;
    int[] dx = new int[]{0, 1, 0, -1};
    int[] dy = new int[]{-1, 0, 1, 0};

    public int solution(int[][] board) {
        N = board.length;
        visited = new int[N][N][4]; // 방향도 고려

        return bfs(board);
    }

    public int bfs(int[][] board) {
        int cost = Integer.MAX_VALUE;

        Node node = new Node(0, 0, -1, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.y == N - 1 && cur.x == N - 1) {
                cost = Math.min(cost, cur.cost);
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] == 1) {
                    continue;
                }

                int nCost = cur.cost;
                if (cur.direction == -1 || cur.direction == i) {
                    nCost += 100;
                } else {
                    nCost += 600;
                }

                if (visited[ny][nx][i] == 0 || board[ny][nx] >= nCost) { // 방문 안한 경우, 비용이 더 저렴한 경우
                    visited[ny][nx][i] = 1;
                    board[ny][nx] = nCost;
                    queue.add(new Node(ny, nx, i, nCost));
                }
            }
        }

        return cost;
    }

    class Node {
        int x;
        int y;
        int direction;
        int cost;

        Node(int y, int x, int direction, int cost) {
            this.y = y;
            this.x = x;
            this.direction = direction;
            this.cost = cost;
        }
    }
}
