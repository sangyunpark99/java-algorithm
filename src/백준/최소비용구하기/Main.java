package 백준.최소비용구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static List<List<Node>> graph = new ArrayList<>();
    private static int[] dist;

    private static final int MAX_VALUE = 100_000_001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N + 1];

        Arrays.fill(dist,MAX_VALUE);

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, weight));
        }


        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        System.out.println(findPath(start,end));
    }

    private static int findPath(int start, int end) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.weight - o2.weight);
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll(); // 무조건 최단 경로

            if(cur.number == end) {
                return dist[end];
            }

            for(int i = 0; i < graph.get(cur.number).size(); i++) {
                Node next = graph.get(cur.number).get(i);
                int newDist = dist[cur.number] + next.weight;
                if(dist[next.number] > newDist) {
                    dist[next.number] = newDist;
                    pq.add(new Node(next.number, newDist));
                }
            }
        }

        System.out.println(Arrays.toString(dist));

        return dist[end];
    }

    public static class Node {
        int number;
        int weight;

        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }
}
