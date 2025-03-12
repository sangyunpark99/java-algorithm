package 백준.웜홀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 음수 사이클이 존재하는지 구하는 문제

    private static int tc;
    private static List<Edge> edges;
    private static final int INF = 10_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();

            for(int i = 0; i < m; i++) { // 도로
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(start, end, weight));
                edges.add(new Edge(end, start, weight));
            }

            for(int i = 0; i < w; i++) { // 블랙홀
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(start, end, -weight));
            }

            boolean hasCycle = bellmanFord(n);

            sb.append(hasCycle ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    public static boolean bellmanFord(int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        boolean updated = false;
        for(int i = 1; i <= n; i++) {
            updated = false;
            for(Edge edge: edges) {
                if(dist[edge.end] > dist[edge.start] + edge.weight) {
                    dist[edge.end] = dist[edge.start] + edge.weight;
                    updated = true;
                }
            }
            if(!updated) break;
        }

        if(updated) {
            for(int i = 1; i <= n; i++) {
                for(Edge edge: edges) {
                    if(dist[edge.end] > dist[edge.start] + edge.weight) {
                        return true;
                    }
                }
            }
        }

        return false;
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