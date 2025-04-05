package 백준.음악프로그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] isDegree = new int[n + 1];

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int cur = arr[1];
            for(int j = 2; j < arr.length; j++) {
                graph.get(cur).add(arr[j]);
                isDegree[arr[j]]++;
                cur = arr[j];
            }
        }

        for(int i = 1; i <= n; i++) {
            if(isDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> answer = new ArrayList<>();

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            answer.add(cur);

            for(int next: graph.get(cur)) {
                isDegree[next]--;
                if(isDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if(answer.size() != n) {
            System.out.println(0);
            return;
        }

        for (Integer i : answer) {
            System.out.println(i);
        }
    }
}
