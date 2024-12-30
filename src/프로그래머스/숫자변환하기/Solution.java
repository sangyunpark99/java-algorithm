package 프로그래머스.숫자변환하기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int x, int y, int n) {

        // DFS면 BFS를 항상 같이 떠올려라ㅅ

        Queue<int[]> queue = new LinkedList<>();

        boolean[] visited = new boolean[1000000 * 3];

        queue.add(new int[]{x, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == y) {
                return cur[1];
            }

            if (!visited[cur[0] + n] && cur[0] + n <= y) {
                queue.add(new int[]{cur[0] + n, cur[1] + 1});
                visited[cur[0] + n] = true;
            }

            if (!visited[cur[0] * 2] && cur[0] * 2 <= y) {
                queue.offer(new int[]{cur[0] * 2, cur[1] + 1});
                visited[cur[0] * 2] = true;
            }

            if (!visited[cur[0] * 3] && cur[0] * 3 <= y) {
                queue.add(new int[]{cur[0] * 3, cur[1] + 1});
                visited[cur[0] * 3] = true;
            }
        }
        return -1;
    }
}
