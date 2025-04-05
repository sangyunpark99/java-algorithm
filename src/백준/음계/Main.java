package 백준.음계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean isAscending = array[0] < array[1];
        boolean isDescending = array[0] > array[1];
        boolean isMixed = false;

        for(int i = 1; i < array.length; i++) {
            if(Math.abs(array[i] - array[i-1]) > 1) {
                isMixed = true;
                break;
            }
        }

        if(isMixed) {
            System.out.println("mixed");
            return;
        }

        if(isAscending) {
            System.out.println("ascending");
            return;
        }

        if(isDescending) {
            System.out.println("descending");
        }
    }
}
