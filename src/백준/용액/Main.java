package 백준.용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int l = 0;
        int r = values.length - 1;
        long check = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while (l < r) {
            long diff = values[l] + values[r];

            if (Math.abs(diff) < check) {
                check = Math.abs(diff);
                answer[0] = values[l];
                answer[1] = values[r];
            }

            if (diff < 0) {
                l++;
            } else {
                r--;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}

