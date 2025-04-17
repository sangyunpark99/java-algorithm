package 백준.구간합구하기펜윅트리;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static long[] arr;
    public static long[] tree;

    private static int N;
    private static int M;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[4 * N];

        for(int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            update(i, arr[i]); // update로 초기화
        }

        System.out.println(Arrays.toString(tree));

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(b, diff);
            } else if (a == 2) {
                sb.append(sum((int) c) - sum(b - 1)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void update(int i, long diff) {
        while(i <= N) {
            tree[i] += diff;
            i += (i & -i); // 자식 노드로 이동
        }
    }

    public static void update(int i, int diff) {
        while(i <= N) {
            tree[i] += diff;
            i += (i & -i);
        }
    }

    public static long sum(int i) {
        long sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & -i);
        }

        return sum;
    }

    // 펜윅트리 - 반복문
    // 세그먼
}
