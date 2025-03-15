package 백준.합이0인네정수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[n];
        int[] D = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int size = n * n;
        int[] AB = new int[size];
        int[] CD = new int[size];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                AB[idx++] = A[i] + B[j];
            }
        }

        idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                CD[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(CD);

        long ans = 0;
        for (int sum : AB) {
            int target = -sum;
            int lower = lowerBound(CD, target);
            int upper = upperBound(CD, target);
            ans += (upper - lower);
        }

        System.out.println(ans);
    }

    private static int lowerBound(int[] arr, int target) { // 찾으려는 수 찾기
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private static int upperBound(int[] arr, int target) { // 찾으려는 수보다 하나 더 큰 수 찾기
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
