package 프로그래머스.리틀프렌즈사천성;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {

    private final int[] dy = {-1, 0, 1, 0};
    private final int[] dx = {0, 1, 0, -1};
    private int M, N;
    private char[][] boardArr;
    private List<Character> tileTypes = new ArrayList<>();
    private Map<Character, List<int[]>> tilePositions = new HashMap<>();
    private String result = "";

    public String solution(int m, int n, String[] board) {
        this.M = m;
        this.N = n;
        this.boardArr = new char[M][N];

        for (int i = 0; i < m; i++) {
            boardArr[i] = board[i].toCharArray();
            for (int j = 0; j < n; j++) {
                char ch = boardArr[i][j];
                if (Character.isUpperCase(ch)) {
                    tilePositions.computeIfAbsent(ch, k -> new ArrayList<>()).add(new int[]{i, j});
                    if (!tileTypes.contains(ch)) {
                        tileTypes.add(ch);
                    }
                }
            }
        }

        Collections.sort(tileTypes);
        StringBuilder sb = new StringBuilder();

        while (!tileTypes.isEmpty()) {
            boolean removed = false;

            for (int i = 0; i < tileTypes.size(); i++) {
                char ch = tileTypes.get(i);
                List<int[]> pos = tilePositions.get(ch);
                int[] start = pos.get(0);
                int[] end = pos.get(1);

                if (canConnect(start, end)) {
                    boardArr[start[0]][start[1]] = '.';
                    boardArr[end[0]][end[1]] = '.';
                    tileTypes.remove(i);
                    sb.append(ch);
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                return "IMPOSSIBLE";
            }
        }

        return sb.toString();
    }

    private boolean canConnect(int[] start, int[] end) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[M][N][4];

        queue.offer(new Node(start[0], start[1], -1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.y == end[0] && node.x == end[1]) {
                return true;
            }
            if (node.curve > 1) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int ny = node.y + dy[d];
                int nx = node.x + dx[d];

                if (!isInBounds(ny, nx)) {
                    continue;
                }

                int newCurve = (node.dir == -1 || node.dir == d) ? node.curve : node.curve + 1;
                if (newCurve > 1 || visited[ny][nx][d]) {
                    continue;
                }

                if (boardArr[ny][nx] != '.' && !(ny == end[0] && nx == end[1])) {
                    continue;
                }

                visited[ny][nx][d] = true;
                queue.offer(new Node(ny, nx, d, newCurve));
            }
        }

        return false;
    }

    private boolean isInBounds(int y, int x) {
        return 0 <= y && y < M && 0 <= x && x < N;
    }

    private static class Node {
        int y, x, dir, curve;

        Node(int y, int x, int dir, int curve) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.curve = curve;
        }
    }
}