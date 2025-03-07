package algorithm_basic;


import java.util.ArrayList;
import java.util.List;

public class BitMasking {
    public static void main(String[] args) {

        int n = 10;

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < (1 << n); i++) {
            for(int j = 0; j < n; j++) {
                if((i & (i << j)) != 0) {
                    System.out.println(Integer.toBinaryString(j));
                }
            }
            System.out.println();
        }
        long endTime = System.currentTimeMillis();
//
//        System.out.println(startTime);
//        System.out.println(endTime);
//        System.out.printf("비트 마스킹 : %d\n", (int)(endTime - startTime));


//        startTime = System.currentTimeMillis();
//        for(int i = 1; i <= 10000000; i++) {
//            combi(1, 0, new ArrayList<>(), i);
//        }
//        endTime = System.currentTimeMillis();
//
//        System.out.println(startTime);
//        System.out.println(endTime);
//        System.out.printf("조합 : %d\n", (int) (endTime - startTime));
    }

    public static void combi(int start, int depth, List<Integer> list, int end) {
        if(depth == end) {
            //System.out.println(list);
            return;
        }

        for(int i = start; i <= 10; i++) {
            list.add(i);
            combi(i + 1, depth + 1, list, end);
            list.removeLast();
        }
    }
}
