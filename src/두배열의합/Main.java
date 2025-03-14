package 두배열의합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static HashMap<Integer, Integer> aSum= new HashMap<>();
    private static HashMap<Integer, Integer> bSum = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

       getSubArraySumCnt(a, aSum);
       getSubArraySumCnt(b, bSum);

       long answer = 0;

       for(Integer key : aSum.keySet()) {
           int diff = T - key;
           if(bSum.containsKey(T - key)) {
               answer += (long)aSum.get(key) * bSum.get(diff);
           }
       }

        System.out.println(answer);
    }

    private static void getSubArraySumCnt(int[] arr, Map<Integer, Integer> sumCnt) {
        int n = arr.length;

        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) { // 부분합
                sum += arr[j];
                sumCnt.put(sum,sumCnt.getOrDefault(sum,0) + 1); // 중복되는 것도 카운팅
            }
        }
    }
}
