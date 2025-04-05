package 백준.다각형의면적;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 점 개수

        long[] x = new long[N];
        long[] y = new long[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        double area = 0;

        for (int i = 0; i < N; i++) {
            int next = (i + 1) % N;
            area += (x[i] * y[next]) - (x[next] * y[i]);
        }

        area = Math.abs(area) / 2.0;

        System.out.printf("%.1f\n", area);
    }
}
