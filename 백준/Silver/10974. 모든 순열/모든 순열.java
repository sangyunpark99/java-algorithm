import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        find(0,new int[n], new boolean[n]);
    }

    public static void find(int depth, int[] arr, boolean[] visited) {
        if(depth == n) {
            print(arr);
        }

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = i+1;
                find(depth + 1, arr, visited);
                visited[i] = false;
            }
        }
    }

    private static void print(int[] arr) {
        String value = "";
        for(int i = 0; i < arr.length; i++) {
            value += arr[i] + " ";
        }

        System.out.println(value);
    }
}