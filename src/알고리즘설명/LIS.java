package 알고리즘설명;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS {

    private static int[] arr;
    private static int[] cnt;
    private static int[] trace;

    public static void main(String[] args) {
        arr = new int[]{10,20,30,20,25,30,40,50};
        cnt = new int[arr.length];

        int lis = 0;
        trace = new int[50];
        Arrays.fill(trace, -1);
        int idx = 0;
        for(int i = 0; i < arr.length; i++) { // 배열 값
            for(int j = 0; j < i; j++) { // 이전 값
                if(arr[j] < arr[i] && cnt[i] < cnt[j] + 1) { // 크기 비교 및 증가 부분 수열 갯수 비교
                    cnt[i] = cnt[j] + 1;
                    trace[i] = j;

                    if(lis < cnt[i]) {
                        lis = cnt[i];
                        idx = i;
                    }
                }
            }
        }

        List<Integer> lisNumber = new ArrayList<>();
        printLIS(idx, lisNumber);

        System.out.println("최장 증가 부분 수열 : " + lisNumber.reversed());
    }

    private static void printLIS(int index, List<Integer> lisNumber) {
        if(index == -1) return;
        lisNumber.add(arr[index]);
        printLIS(trace[index], lisNumber);
    }
}
