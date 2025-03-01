package algorithm_basic;

import java.util.Arrays;

public class Permutation {

    private static String[] array = {"A","B","C"};

    public static void main(String[] args) {
        permutation(new String[2], new boolean[3], 0);
    }

    public static void permutation(String[] result, boolean[] visited, int depth) {
        if(depth == 2) {
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i = 0; i < array.length; i++) {
            if(!visited[i]) {
                result[depth] = array[i];
                visited[i] = true;
                permutation(result, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
}
