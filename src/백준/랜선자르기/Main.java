package 백준.랜선자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int K;
    private static int N;
    private static List<Integer> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        // 이분 탐색 ? 11개를 맞출 수 있어?
        long maxLine = Integer.MIN_VALUE;
        for(int i = 0; i < K; i++) {
            int value = Integer.parseInt(br.readLine());
            maxLine = Math.max(value, maxLine);
            lines.add(value);
        }

        System.out.println(findCutLine(maxLine));
    }

    private static int findCutLine(long value) {
        // 1부터라 이미 정렬 되어 있음
        long start = 1;
        long end = value;
        long answer = 0;

        while(start <= end) {
            long mid = (start + end) / 2;
            if(check(mid)) { // 더 크게 잘라도 된다.
                answer = mid;
                start = mid + 1;
            }else { // 더 크게
                end = mid - 1;
            }
        }

        return (int) answer;
    }

    private static boolean check(long n) {
        int totalLine = 0;
        for (int length : lines) {
            totalLine += length / n;
        }
        return totalLine >= N;
    }
}