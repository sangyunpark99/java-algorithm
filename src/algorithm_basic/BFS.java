package algorithm_basic;

import java.util.*;

public class BFS {

    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) {

        for(int i = 0; i < 9; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).addAll(List.of(1,2,3));
        graph.get(1).addAll(List.of(4,5));
        graph.get(2).add(6);
        graph.get(3).addAll(List.of(7,8));

         bfs();
    }

    private static void bfs() {

        int[] visited = new int[9];

        Arrays.fill(visited, -1);
        visited[0] = 0;
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            System.out.println(cur + "번째 노드, 거리 : " + visited[cur]);

            for(int next : graph.get(cur)) {
                if(visited[next] == -1) {
                    visited[next] = visited[cur] + 1;
                    queue.add(next);
                }
            }
        }
    }
}
