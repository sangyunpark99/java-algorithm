package algorithm_basic;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private static List<List<Integer>> tree = new ArrayList<>();

    public static void main(String[] args) {

        for(int i = 0; i < 5; i++) {
            tree.add(new ArrayList<>());
        }


        tree.get(0).addAll(List.of(1,2));

        tree.get(2).addAll(List.of(3,4));

        System.out.print("전위 순회 : ");
        preOrder(0, new boolean[5]);
        System.out.println();
        System.out.print("중위 순회 : ");
        inOrder(0, new boolean[5]);
        System.out.println();
        System.out.print("후위 순회 : ");
        postOrder(0, new boolean[5]);
    }

    private static void preOrder(int curNode, boolean[] visited) { // 전위 순회 나 - 왼쪽 자식 - 오른쪽 자식

        print(curNode);

        if(tree.get(curNode).isEmpty()) {
            return;
        }

        if(!visited[curNode]) {
            visited[curNode] = true; // 자기 자신
            preOrder(tree.get(curNode).get(0), visited); // 왼쪽 자식
            preOrder(tree.get(curNode).get(1), visited); // 오른쪽 자식
        }
    }

    private static void inOrder(int curNode, boolean[] visited) { // 중위 순회

        if(tree.get(curNode).isEmpty()) {
            print(curNode);
            return;
        }

        if(!visited[curNode]) {
            inOrder(tree.get(curNode).get(0), visited);
            print(curNode);
            visited[curNode] = true;
            inOrder(tree.get(curNode).get(1), visited);
        }
    }

    private static void postOrder(int curNode, boolean[] visited) { // 후위 순회 왼(자) - 오(자) - 중

        if(tree.get(curNode).isEmpty()) {
            print(curNode);
            return;
        }

        if(!visited[curNode]) {
            postOrder(tree.get(curNode).get(0),visited);
            postOrder(tree.get(curNode).get(1),visited);
            visited[curNode] = true;
            print(curNode);
        }
    }

    private static void print(int value) {
        System.out.print(Character.toUpperCase((char) (value + 97)) + " ");
    }

}
