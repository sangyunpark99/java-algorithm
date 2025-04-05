package 백준.줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 일부 학생들의 키를 비교한 결과가 주어진 경우, 줄을 세우는 프로그램
    // 위상 정렬
    // 순서가 존재한다? 위상 정렬의심
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] isDegree = new int[n + 1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            isDegree[end]++;
        }

        Deque<Integer> dequeue = new ArrayDeque<>();

        for(int i = 1; i < isDegree.length; i++) {
            if(isDegree[i] == 0) {
                dequeue.add(i);
            }
        }

        List<Integer> list = new ArrayList<>();

        while(!dequeue.isEmpty()) {

            int cur = dequeue.poll();
            list.add(cur);

            for(int next: graph.get(cur)) {
                isDegree[next]--;
                if(isDegree[next] == 0) {
                    dequeue.addFirst(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}
