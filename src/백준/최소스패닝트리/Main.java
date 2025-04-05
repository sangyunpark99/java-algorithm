package 백준.최소스패닝트리;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int v;
    private static int e;
    private static int[] parent;
    private static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v + 1];
        edges = new ArrayList<>();

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, weight));
        }

        // 가중치 크기순으로 정렬
        edges.sort((o1,o2) -> o1.weight - o2.weight);

        for(int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        int mstWeight = 0;
        int edgeCnt = 0;

        for(Edge edge: edges) {
            if(union(edge.u, edge.v)) {
                mstWeight += edge.weight;
                edgeCnt++;
                if(edgeCnt == v - 1) break;
            }
        }

        System.out.println(mstWeight);
    }

    private static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false; // 같은 집합인 경우

        if(rootA < rootB) parent[rootB] = rootA;
        else parent[rootA] = rootB;

        return true; // 같은 집합이 아닌 경우
    }

    public static class Edge {
        int u;
        int v;
        int weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
}
