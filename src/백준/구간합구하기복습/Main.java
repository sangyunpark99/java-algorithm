package 백준.구간합구하기복습;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static long[] arr;
    public static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[4 * N];

        for(int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 트리의 노드는 1번부터 시작
        init(1, N, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // 수 변경
                long dif = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, dif);
            } else if (a == 2) { // 구간의 합 구하기
                sb.append(sum(1, N, 1, b, (int) c) + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();

    }

    // 리프 노드까지 내려간 후, 리프 노드에서 부모 노드로 돌아오면서 왼쪽 + 오른쪽 값을 더해준다.
    public static long init(int start, int end, int node) {
        if(start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1); // 더해줌
    }

    public static void update(int start, int end, int node, int idx, long dif) {
        if(idx < start || idx > end) {
            return;
        }

        tree[node] += dif; // 차이 더해주기

        if(start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, dif); // 리프 노드 탐색
        update(mid + 1, end, node * 2 + 1, idx, dif); // 리프 노드 탐색
    }

    public static long sum(int start, int end, int node, int left, int right) { // 특정 구간의 합을 빠르게 계산하고 싶을때 사용
        if(left > end || right < start) { // 범위를 벗어난 경우
            return 0;
        }

        if(left <= start && end >= right) { // 완전히 포함된 경우
            return tree[node];
        }

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }
}
