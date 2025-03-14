package 백준.세용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 3가지 용액을 혼합한 용액
    // 0에 가장 가까운 용액 만들기
    // 5000 이하
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(array);

        long curSum = Long.MAX_VALUE;
        int a = 0;
        int b = 0;
        int c = 0;

        for (int i = 0; i < n - 2; i++) {
            int cur = array[i];
            int l = i + 1; // 이전 숫자는 이미 체크됨
            int r = n - 1;

            while (l < r) {
                long sum = (long) cur + array[l] + array[r];

                if (Math.abs(sum) < Math.abs(curSum)) {
                    curSum = sum;
                    a = cur;
                    b = array[l];
                    c = array[r];
                }

                if (sum < 0) { // 음수인 경우 양수 만들기
                    l++;
                } else { // 양수인 경우 0에 가깝게 만들기
                    r--;
                }
            }
        }

        System.out.println(a + " " + b + " " + c);
    }
}
