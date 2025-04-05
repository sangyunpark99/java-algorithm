package 백준.수나누기게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[] score;
    private static boolean[] isScore;
    private static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        score = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        isScore = new boolean[1000001];

        int maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < score.length; i++) { // O(10만)
            maxValue = Math.max(maxValue, score[i]);
            isScore[score[i]] = true;
        }

        cnt = new int[maxValue + 1];

        for(int i = 0; i < score.length; i++) { // O(10만)
            int curScore = score[i];
            for(int j = 1; j <= maxValue / curScore; j++) {
                if(isScore[curScore * j]) {
                    cnt[curScore * j] -= 1;
                    cnt[curScore] += 1;
                }
            }
        }

        for (int i = 0; i < score.length; i++) {
            System.out.print(cnt[score[i]] + " ");
        }
    }
}
