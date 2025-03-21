package 백준.좋은친구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int K;
    private static List<String> names = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] cnt = new int[21];

        Queue<Integer> queue = new LinkedList<>();

        long answer = 0;

        for(int i = 0 ; i < N; i++) {
            String name = br.readLine();
            names.add(name);
        }

        for(int i = 0; i < N; i++) {
            String name = names.get(i);
            int len = name.length();

            answer += cnt[len];

            queue.offer(len);
            cnt[len]++;

            if(queue.size() > K) {
                int removeLen = queue.poll();
                cnt[removeLen]--;
            }
        }

        System.out.println(answer);
    }
}
