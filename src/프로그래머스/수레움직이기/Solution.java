package 프로그래머스.수레움직이기;

import java.util.*;

class Solution {

    private final int[] dy = {-1, 0, 1, 0};
    private final int[] dx = {0, 1, 0, -1};

    private int[][][][] dp = new int[4][4][4][4];
    private boolean[][][][] visited = new boolean[4][4][4][4];

    private int redStartY, redStartX;
    private int redEndY, redEndX;
    private int blueStartY, blueStartX;
    private int blueEndY, blueEndX;

    private int n, m;

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;

        // 위치 세팅
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) { redStartY = i; redStartX = j; }
                else if (maze[i][j] == 2) { blueStartY = i; blueStartX = j; }
                else if (maze[i][j] == 3) { redEndY = i; redEndX = j; }
                else if (maze[i][j] == 4) { blueEndY = i; blueEndX = j; }
            }
        }
        go(maze);

        return dp[redEndY][redEndX][blueEndY][blueEndX];
    }

    private void go(int[][] maze) {
        Queue<State> queue = new LinkedList<>();
        boolean[][] redInit = new boolean[4][4];
        boolean[][] blueInit = new boolean[4][4];
        redInit[redStartY][redStartX] = true;
        blueInit[blueStartY][blueStartX] = true;

        queue.offer(new State(redStartY, redStartX, blueStartY, blueStartX, redInit, blueInit));
        visited[redStartY][redStartX][blueStartY][blueStartX] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            int ry = cur.ry, rx = cur.rx, by = cur.by, bx = cur.bx;

            for (int i = 0; i < 4; i++) {
                int nry = ry + dy[i];
                int nrx = rx + dx[i];

                if(ry == redEndY && rx == redEndX) { // 이미 도착지점인 경우
                    nry = redEndY;
                    nrx = redEndX;
                }

                if (!isValid(nry, nrx, maze)) continue;
                //  파란색 도착한 상태인데, 빨간색이 그 자리에 가려는 경우

                for (int j = 0; j < 4; j++) {

                    int nby = by + dy[j];
                    int nbx = bx + dx[j];

                    if(by == blueEndY && bx == blueEndX) { // 이미 도착 지점인 경우
                        nby = blueEndY;
                        nbx = blueEndX;
                    }

                    if (!isValid(nby, nbx, maze)) continue;

                    if (nry == nby && nrx == nbx) continue;
                    if (nry == by && nrx == bx && nby == ry && nbx == rx) continue;

                    if (visited[nry][nrx][nby][nbx]) continue; // 한번 방문한 곳은 continue;

                    boolean[][] redNext = deepCopy(cur.visitedRed);
                    boolean[][] blueNext = deepCopy(cur.visitedBlue);

                    if(!(ry == redEndY && rx == redEndX)) { // 도착지점은 고정이므로 제외
                        if(redNext[nry][nrx]) continue;
                        redNext[nry][nrx] = true;
                    }

                    if(!(by == blueEndY && bx == blueEndX)) { // 도착지점은 고정이므로 제외
                        if(blueNext[nby][nbx]) continue;
                        blueNext[nby][nbx] = true;
                    }

                    dp[nry][nrx][nby][nbx] = dp[ry][rx][by][bx] + 1;
                    visited[nry][nrx][nby][nbx] = true;
                    queue.offer(new State(nry, nrx, nby, nbx, redNext, blueNext));
                }
            }
        }
    }

    private boolean[][] deepCopy(boolean[][] original) {
        boolean[][] copy = new boolean[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                copy[i][j] = original[i][j];
            }
        }

        return copy;
    }

    public static class State {
        int ry, rx, by, bx;
        boolean[][] visitedRed;
        boolean[][] visitedBlue;

        public State(int ry, int rx, int by, int bx, boolean[][] visitedRed, boolean[][] visitedBlue) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.visitedRed = visitedRed;
            this.visitedBlue = visitedBlue;
        }
    }

    private boolean isValid(int y, int x, int[][] maze) {
        return y >= 0 && y < n && x >= 0 && x < m && maze[y][x] != 5;
    }
}