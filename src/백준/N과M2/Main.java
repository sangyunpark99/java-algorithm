package 백준.N과M2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        permu(n, new int[m], 0, m, 1);
    }

    public static void permu(int n, int[] array, int depth, int m, int start) {

        if(depth == m) {
            for(int i  = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = start; i <= n; i++) {
                array[depth] = i;
                permu(n, array, depth + 1, m, i + 1);
            }
    }
}
