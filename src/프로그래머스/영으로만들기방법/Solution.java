package 프로그래머스.영으로만들기방법;

import java.util.*;

class Solution {

    private long totalMove = 0;
    private List<Integer>[] graph;
    private boolean[] visited;

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[] {
                        -5,0,2,1,2
                },new int[][]{
                        {0,1},
                        {3,4},
                        {2,3},
                        {0,3}
                }
        ));
    }

    public long solution(int[] a, int[][] edges) {
        long sum = 0;

        for(int i = 0; i < a.length; i++) {
            sum += a[i];
        }

        if(sum != 0) return -1; // 전부 더한 경우 0이 되야 가능


        int n = a.length;

        graph = new ArrayList[n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        dfs(0, a);

        return totalMove;
    }

    private long dfs(int curNode, int[] a) {
        visited[curNode] = true;
        long weight = a[curNode];

        for(int i = 0; i < graph[curNode].size(); i++) {
            int next = graph[curNode].get(i);

            if(!visited[next]) {
                weight += dfs(next, a);
            }
        }

        totalMove += Math.abs(weight);
        return weight;
    }
}
