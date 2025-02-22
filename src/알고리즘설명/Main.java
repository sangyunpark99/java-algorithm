package 알고리즘설명;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        int[] arr = new int[]{10,20,30,25,30,20,40,50};

        List<Integer> lis = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (lis.isEmpty() || lis.get(lis.size() - 1) < num) {
                lis.add(num);
            } else {
                int left = 0;
                int right = lis.size();

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (lis.get(mid) < num) { // 더 작은 경우
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                lis.set(left, num); // 부분 증가 수열 갱신
            }
        }

        System.out.println(lis);
    }
}
