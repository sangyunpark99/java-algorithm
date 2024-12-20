package 프로그래머스.합승택시요금;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    private List<List<Node>> graph = new ArrayList<>();
    private int[] distFromS;
    private int[] distFromA;
    private int[] distFromB;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) { // 1~n번째 노드까지
            graph.add(new ArrayList<>());
        }

        // 그래프 먼저 그리기
        // class 형식으로 번호랑 가중치 같이
        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int weight = fare[2];

            graph.get(start).add(new Node(end, weight));
            graph.get(end).add(new Node(start, weight));
        }

        distFromS = findShortPath(n, s, graph); // O(V + E)
        distFromA = findShortPath(n, a, graph); // O(V + E)
        distFromB = findShortPath(n, b, graph); // O(V + E)
        // O(3V+3E+n)

        for (int i = 1; i <= n; i++) { // O(n) -> 최대 200
            int cost = distFromS[i] + distFromA[i] + distFromB[i];
            answer = Math.min(answer, cost);
        }

        return answer;
    }

    // A 탐색
    public int[] findShortPath(int n, int start, List<List<Node>> graph) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.weight - o2.weight;
            }
        });

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int number = cur.number;
            int weight = cur.weight;

            if (weight > dist[number]) { // 기존 보다 더 긴 경우 넘기기
                continue;
            }

            for (Node node : graph.get(number)) {
                int nextNumber = node.number;
                int nextWeight = node.weight;

                if (dist[nextNumber] > weight + nextWeight) { // 기존 값 vs 현재까지 누적 이동 + 이동
                    dist[nextNumber] = weight + nextWeight;
                    pq.add(new Node(nextNumber, dist[nextNumber])); // 기존 거리까지
                }
            }
        }

        return dist;
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
