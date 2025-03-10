package 백준.도시분할계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static List<Edge> edges = new ArrayList<>();
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end, weight));
        }

        parent = new int[N+1];
        for(int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        Collections.sort(edges, (o1, o2) -> o1.weight - o2.weight);

        int mstCost = 0;
        int maxEdge = 0;
        int count = 0;

        for(int i = 0; i < edges.size(); i++) {
            Edge cur = edges.get(i);
            if(union(cur.start, cur.end)) {
                mstCost += cur.weight;
                maxEdge = cur.weight;
                count++;
            }

            if(count == N - 1) { // 간서은 노드의 갯수 - 1
                break;
            }
        }

        System.out.println(mstCost - maxEdge);// 제일 비싼 비용 빼줌으로써 마을을 2개로 분리합니다.
    }

    private static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        if(rootA < rootB) {
            parent[rootA] = rootB;
        }else {
            parent[rootB] = rootA;
        }

        return true;
    }

    public static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
