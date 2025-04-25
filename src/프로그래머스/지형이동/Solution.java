package 프로그래머스.지형이동;

import java.util.*;

class Solution {
    // 1. DFS로 영역 구분
    // 2. 서로 다른 영역 사이의 가장 작은 사다리 비용 계산
    // 3. 각 영역의 간선 리스트를 비용 기준으로 정렬한 후 크루스칼 알고리즘 사용
    // 4. N개의 영역을 모두 연결한 비용
    // 메모리 초과?
    private int[][] areaMap;
    private int n;
    private int[] dy = {-1,0,1,0};
    private int[] dx = {0,1,0,-1};
    private int[][] Land;
    private int Height;
    private List<Edge> edges = new ArrayList<>();

    private int[] parent;

    public int solution(int[][] land, int height) {
        int answer = 0;

        n = land.length;
        areaMap = new int[n][n];
        Land = land;
        Height = height;
        boolean[][] visited = new boolean[n][n];

        // 1. DFS로 영역 색칠 O(n) = 90000
        int colorNumber = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    visited[i][j] = true;
                    areaMap[i][j] = colorNumber;
                    color(i, j, colorNumber++, visited); // 영역 칠하기
                }
            }
        }

        // 2. 서로 다른 영역 사이의 가장 작은 사다리 비용 계산

        findMinGap();

        // 3.  각 영역의 간선 리스트를 비용 기준으로 정렬한 후 크루스칼 알고리즘 사용

        Collections.sort(edges,(o1,o2) -> o1.weight - o2.weight);

        parent = new int[colorNumber];
        for(int i = 0; i < colorNumber; i++) {
            parent[i] = i;
        }

        int connected = 0;
        // 4. N개의 영역을 모두 연결한 비용
        for(int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if(union(edge.a, edge.b)) {
                answer += edge.weight;
                connected++;
                if(connected == colorNumber - 1) break;
            }
        }

        return answer;
    }

    private boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) { // 이미 부모가 같은 경우
            return false;
        }

        if(parentA < parentB) { // 더 작은 값을 기준으로
            parent[parentB] = parentA;
        }else {
            parent[parentA] = parentB;
        }

        return true; // 부모가 다른 경우
    }

    private int find(int value) {
        if(parent[value] == value) {
            return value;
        }

        return parent[value] = find(parent[value]);
    }

    public class Edge {
        int a;
        int b;
        int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    private void findMinGap() {
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                for(int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if(ny < 0 || ny >= n || nx < 0 || nx >= n) continue;

                    int aArea = areaMap[y][x];
                    int bArea = areaMap[ny][nx];

                    if(aArea < bArea) { // 다른 영역인 경우
                        int gap = Math.abs(Land[y][x] - Land[ny][nx]);
                        edges.add(new Edge(aArea, bArea, gap));
                    }
                }
            }
        }
    }

    private void color(int y, int x, int colorNumber, boolean[][] visited) {
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= n || nx < 0 || nx >= n || visited[ny][nx]) continue;

            int heightDiff = Math.abs(Land[y][x] - Land[ny][nx]);
            if(heightDiff <= Height) {
                visited[ny][nx] = true;
                areaMap[ny][nx] = colorNumber;
                color(ny, nx, colorNumber, visited);
            }
        }
    }
}
