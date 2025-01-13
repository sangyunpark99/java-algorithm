package 프로그래머스.lv3양과늑대;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public static int maxSheep = Integer.MIN_VALUE;

    public int solution(int[] info, int[][] edges) {

        List<Integer>[] tree = new ArrayList[info.length];

        for (int i = 0; i < info.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }

        List<Integer> candidates = new ArrayList<>();
        candidates.add(0);
        dfs(0, 0, 0, info, tree, candidates);

        return maxSheep;
    }

    private void dfs(int sheep, int wolf, int curNode, int[] info, List<Integer>[] tree, List<Integer> candidates) {

        if (info[curNode] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (sheep <= wolf) {
            return;
        }

        maxSheep = Math.max(maxSheep, sheep);

        List<Integer> newCandidates = new ArrayList<>(candidates);
        newCandidates.remove(Integer.valueOf(curNode)); // 인덱스를 제거한다.
        newCandidates.addAll(tree[curNode]); // 후보 전부 추가

        for (int nextNode : newCandidates) { // 이렇게 방문하지 않은 지점도 방문할 수 있다.
            dfs(sheep, wolf, nextNode, info, tree, newCandidates);
        }

    }
}
