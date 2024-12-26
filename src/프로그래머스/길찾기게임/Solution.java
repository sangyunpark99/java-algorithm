package 프로그래머스.길찾기게임;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }

        Collections.sort(nodes, (o1, o2) -> {

            if (o1.y == o2.y) {
                return o1.x - o2.x;
            }

            return o2.y - o1.y;
        });

        Node root = makeTree(nodes);
        List<Integer> preOrderResult = new ArrayList<>();
        preOrder(root, preOrderResult);

        List<Integer> postOrderResult = new ArrayList<>();
        postOrder(root, postOrderResult);

        // 트리 구조를 어떻게 만들어야 할까?
        
        answer[0] = preOrderResult.stream().mapToInt(o -> o).toArray();
        answer[1] = postOrderResult.stream().mapToInt(o -> o).toArray();

        return answer;
    }

    // 전위 순회 루트 -> 왼 -> 오
    public void preOrder(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.number); // 중앙
            preOrder(node.leftNode, result); // 왼쪽
            preOrder(node.rightNode, result); // 오른쪽
        }
    }

    // 후위 순회 왼 -> 오 -> 가운데
    public void postOrder(Node node, List<Integer> result) {
        if (node != null) {
            postOrder(node.leftNode, result); // 왼쪽
            postOrder(node.rightNode, result); // 오른쪽
            result.add(node.number);
        }
    }

    // 추가 - 중위 순회 : 왼 -> 가운데 -> 오른쪽
    public Node makeTree(List<Node> nodes) {

        Node root = nodes.get(0);

        for (int i = 1; i < nodes.size(); i++) {
            addNode(root, nodes.get(i));
        }

        return root;
    }

    public void addNode(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.leftNode == null) {
                parent.leftNode = child;
            } else { // 이미 leftNode가 존재하는 경우
                addNode(parent.leftNode, child);
            }
        } else {
            if (parent.rightNode == null) {
                parent.rightNode = child;
            } else { // 이미 rightNode가 존재하는 경우
                addNode(parent.rightNode, child);
            }
        }
    }

    static class Node {
        int number;
        int y;
        int x;
        Node leftNode;
        Node rightNode;

        public Node(int x, int y, int number) {
            this.number = number;
            this.y = y;
            this.x = x;
        }
    }
}
