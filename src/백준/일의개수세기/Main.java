package 백준.일의개수세기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long result = countOnes(B) - countOnes(A - 1);
        System.out.println(result);
    }

    static long countOnes(long n) {
        if (n == 0) return 0;

        int m = 63 - Long.numberOfLeadingZeros(n);
        long p = 1L << m;  // 2^m
        long r = n - p;

        return m * (p >> 1) + (r + 1) + countOnes(r);
    }
}
