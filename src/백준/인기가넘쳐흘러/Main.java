package 백준.인기가넘쳐흘러;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int k;
    private static int t;

    private static int[] cnt = new int[301];
    private static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for (int j = start; j < end; j++) { // t명 이상 존재하는 경우 그냥 t명이 있다고 생각해도 무방
                cnt[i] = Math.min(t, cnt[j] + 1);
            }
        }

        int temp = cnt[1]; // 1부터 시작
        int count = 1;
        for (int i = 2; i <= n; i++) { // 2부터 시작
            if (temp != cnt[i]) {
                nodes.add(new Node(count, temp));
                count = 0; // 초기화
                temp = cnt[i];
            }
            count++; // 시간 차이
        }


    }

    static class Node {
        int time;
        int person;

        public Node(int time, int person) {
            this.time = time;
            this.person = person;
        }
    }
}
