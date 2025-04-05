package 백준.단어맞추기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;

    // A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            String origin = br.readLine();
            char[] value = origin.toCharArray();
            if(next(value)) {
                System.out.println(new String(value));
            }else  {
                System.out.println(origin);
            }
        }

        // 다음 자리에 올 알파벳
        // 1씩 더하면서
    }

    private static boolean next(char[] word) {
        int i = word.length - 2;

        while(i >= 0 && word[i] >= word[i+1]) { // 큰 거 찾기
            i--;
        }

        if(i == -1) {
            return false;
        }

        int j = word.length - 1;

        while(word[j] <= word[i]) {
            j--;
        }

        swap(word, i, j);

        reverse(word, i + 1, word.length - 1);

        return true;
    }

    private static void swap(char[] word, int i, int j) {
        char value = word[i];
        word[i] = word[j];
        word[j] = value;
    }

    private static void reverse(char[] word, int start, int end) {
        while(start < end) {
            swap(word, start, end);
            start++;
            end--;
        }
    }
}
