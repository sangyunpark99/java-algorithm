package 프로그래머스.도넛과막대그래프;

import java.util.*;

class Solution {

    private int findConnectNum;
    private HashMap<Integer,Integer> inDegree = new HashMap<>();
    private HashMap<Integer,Integer> outDegree = new HashMap<>();
    private Map<Integer, List<Integer>> graph = new HashMap<>();
    private int edgeCnt = 0;
    private int nodeCnt = 0;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        for(int[] edge: edges) {
            int a = edge[0]; // from
            int b = edge[1]; // to

            inDegree.put(b, inDegree.getOrDefault(b,0) + 1);
            outDegree.put(a, outDegree.getOrDefault(a,0) + 1);

            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        }

        // 1. 추가된 정점 찾기
        for (int node = 1; node <= edges.length; node++) {
            int in = inDegree.getOrDefault(node, 0);
            int out = outDegree.getOrDefault(node, 0);

            if (in == 0 && out >= 2) {
                findConnectNum = node;
            }
        }

        answer[0] = findConnectNum;

        // 2. 그래프 모양 탐색
        for(int node: graph.get(findConnectNum)) { // 분기점과 연결된 노드들
            edgeCnt = 0;
            nodeCnt = 0;
            HashSet<Integer> visited = new HashSet<>();
            dfs(node, visited);

            if(edgeCnt == nodeCnt) { // 도넛
                answer[1]++;
            }else if(edgeCnt == nodeCnt - 1) { // 막대
                answer[2]++;
            }else if(edgeCnt == nodeCnt + 1) { // 8자
                answer[3]++;
            }
        }

        return answer;
    }

    private void dfs(int node, Set<Integer> visited) {

        if(visited.contains(node)) { // 방문 처리
            return;
        }

        visited.add(node);
        nodeCnt++;

        if(!graph.containsKey(node)) {
            return;
        }
        for(int nextNode: graph.get(node)) {
            edgeCnt++;
            dfs(nextNode, visited);
        }
    }
}
