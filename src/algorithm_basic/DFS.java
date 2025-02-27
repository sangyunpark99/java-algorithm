package algorithm_basic;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    private static List<List<Integer>> nodes = new ArrayList<>();
    private static List<Integer> visitOrder = new ArrayList<>();

    public static void main(String[] args) {

        for(int i = 0; i <= 5; i++) {
            nodes.add(new ArrayList<>());
        }

        nodes.get(1).add(2);
        nodes.get(1).add(3);
        nodes.get(2).add(4);
        nodes.get(2).add(5);

        boolean[] visited = new boolean[6];
        visited[1] = true;
        visitOrder.add(1);
        dfs(1, visited);

        System.out.println("방문 순서 : " + visitOrder);
    }

    private static void dfs(int curNode, boolean[] visited) {

        for(int nextNode : nodes.get(curNode)) {
            if(!visited[nextNode]) {
                visitOrder.add(nextNode);
                visited[nextNode] = true;
                dfs(nextNode, visited);
            }
        }
    }
}
