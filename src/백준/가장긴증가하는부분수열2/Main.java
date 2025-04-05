package 백준.가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int A;
    private static int[] a;
    private static List<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < A; i++) {
            int value = a[i];
            if(lis.isEmpty() || lis.get(lis.size() - 1) < value) {
                lis.add(value);
            }else{
                int left = 0;
                int right = lis.size() - 1;

                while(left < right) {
                    int mid = (left + right) / 2;

                    if(lis.get(mid) < value) {
                        left = mid + 1;
                    }else {
                        right = mid;
                    }
                }

                lis.set(left, value);
            }
        }

        System.out.println(lis.size());
    }

}
