package algorithm_basic;

import java.util.Arrays;

public class Combination {
    private static String[] array = {"A","B","C"};

    public static void main(String[] args) {
        combination(0, new String[2], 0);
    }

    public static void combination(int start, String[] result, int depth) {
        if(depth == 2) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i = start; i < array.length; i++) {
            result[depth] = array[i];
            combination(i + 1, result, depth + 1);
        }
    }
}
