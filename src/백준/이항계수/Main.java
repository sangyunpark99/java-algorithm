package 백준.이항계수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        combi(1,b,a);
        System.out.println(cnt);
    }

    private static void combi(int start, int depth, int len) {
        if(depth == len) {
            cnt++;
            return;
        }

        for(int i = start; i <= len; i++) {
            combi(i + 1, depth + 1, len);
        }
    }
}
