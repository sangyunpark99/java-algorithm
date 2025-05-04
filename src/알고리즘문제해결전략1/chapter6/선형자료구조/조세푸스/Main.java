package 알고리즘문제해결전략1.chapter6.선형자료구조.조세푸스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int testCase;
    private static int M;
    private static int K;

    // 조세푸스와 다른 병사 하나만 살아남았을 때 로마에 항복해서 살아남는다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            measureExecutionTime(Main::solveArrayList, "ArrayList 풀이");
            measureExecutionTime(Main::solveLinkedList, "LinkedList 풀이");
        }
    }

    // ArrayList로 풀기
    private static void solveArrayList() {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= M; i++) { // O(M)
            list.add(i);
        }

        int next = K - 1;
        int removeIdx = 0;
        while(list.size() > 2) { // O(M)
            list.remove(removeIdx);
            removeIdx = (removeIdx + next) % list.size();
        }

        System.out.println(list.get(0) + " " + list.get(1));
    }

    // LinkedList로 풀기
    private static void solveLinkedList() {

        Node head = new Node(1);
        Node prev = head;

        for(int i = 2; i <= M; i++) {
            Node node = new Node(i);
            prev.next = node;
            prev = node;
        }

        prev.next = head; // 원형 연결
        Node cur = prev; // 마지막 노드

        // 맨 처음 노드를 제거
        cur.next = cur.next.next;
        while(cur.next.next != cur) {
            for(int i = 0; i < K - 1; i++) { // 제거할 노드의 이전 노드로 이동
                cur = cur.next;
            }

            cur.next = cur.next.next;
        }

        System.out.println(cur.next.value + " " + cur.value);
    }

    private static class Node {
        Node next;
        int value;

        Node(int value) {
            this.value = value;
        }
    }

    private static void measureExecutionTime(Runnable task, String label) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        System.out.println(label + " 실행 시간: " + (end - start) / 1_000_000.0 + "ms");
    }
}
