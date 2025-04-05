package 백준.문제집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 문제 풀 순서 정하기
    // 순서가 존재한다.
    // 위상정렬인데 Queue에 값을 넣을때, 더 작은 수부터 == 우선순위 큐
    private static int N;
    private static int M;
    private static int[] isDegree;
    private static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isDegree = new int[N + 1];

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            isDegree[end]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i < isDegree.length; i++) {
            if(isDegree[i] == 0) {
                pq.offer(i);
            }
        }

        // 작은 걸 먼저 시작해야 하므로 우선순위 큐 사용
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            System.out.print(cur + " ");
            for(int next: graph.get(cur)) {
                isDegree[next]--;
                if(isDegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }

    }
}
