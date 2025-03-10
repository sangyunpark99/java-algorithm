package 백준.사이클게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int answer = 1;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(!union(a,b)) { // 사이클 생기는 경우
                System.out.println(i + 1);
                return;
            }
        }

        System.out.println(0);
    }

    private static int find(int value){
        if(parent[value] == value) return value;
        return parent[value] = find(parent[value]);
    }


    private static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;

        if(rootA > rootB) {
            parent[rootA] = rootB;
        }else {
            parent[rootB] = rootA;
        }

        return true;
    }
}
