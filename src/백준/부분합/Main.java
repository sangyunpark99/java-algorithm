package 백준.부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int s;
    private static int[] array;
    private static int[] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(find());
    }

    private static int find() {
        int l = 0;
        int r = 0;
        int cnt = Integer.MAX_VALUE;
        int sum = 0;
        while(r < n) {

            sum += array[r++];

             while(sum >= s) {
                 cnt = Math.min(cnt, r - l);
                 sum -= array[l++];
             }
        }

        return cnt == Integer.MAX_VALUE ? 0 : cnt;
    }
}