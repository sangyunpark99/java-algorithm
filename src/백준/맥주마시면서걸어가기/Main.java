package 백준.맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int t;
    private static int n;
    private static int[][] dist;
    private static final int INF = 10_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            n = Integer.parseInt(br.readLine());
            int total = n + 2;

            int[][] locations = new int[total][2];
            dist = new int[total][total];

            for (int i = 0; i < total; i++) {
                st = new StringTokenizer(br.readLine());
                locations[i][0] = Integer.parseInt(st.nextToken()); // x
                locations[i][1] = Integer.parseInt(st.nextToken()); // y
            }

            for (int i = 0; i < total; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            for(int i = 0; i < total; i++) {
                for(int j = 0; j < total; j++) {
                    if(i != j && go(locations[i], locations[j])) { // 1000m 이하인 경우에만 이어줍니다.
                        dist[i][j] = 1;
                    }
                }
            }

            for(int k = 0; k < total; k++) {
                for(int i = 0; i < total; i++) {
                    for(int j = 0; j < total; j++) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            if(dist[0][total - 1] != INF) {
                sb.append("happy\n");
            }else {
                sb.append("sad\n");
            }
        }

        System.out.println(sb);
    }

    private static boolean go(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0])  + Math.abs(p1[1] - p2[1]) <= 1000;
    }
}
