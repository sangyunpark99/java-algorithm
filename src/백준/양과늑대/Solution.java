package 백준.양과늑대;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 0;

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < info.length; i++) {
            nodes.add(new Node(i, info[i]));
        }

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int parent = edge[0];
            int child = edge[1];

            Node curNode = nodes.get(parent);

            if (curNode.leftNode == null) {
                curNode.leftNode = nodes.get(child);
            } else {
                curNode.rightNode = nodes.get(child);
            }
        }

        Node root = nodes.get(0); // 루트 노드

        // 순회하는 방식을 잘 생각해야 한다. 최대한 양을 많이 데려갈 수 있도록

        return answer;
    }

    class Node {

        int number;
        int value; // 양인지 늑대인지 판별
        Node leftNode;
        Node rightNode;

        public Node(int number, int value) {
            this.number = number;
            this.value = value;
        }
    }
}
