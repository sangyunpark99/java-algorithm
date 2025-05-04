package 알고리즘문제해결전략1.chapter6.분할정복.울타리잘라내기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    // 1. 울타리 배열을 절반으로 나눈다.
    // 2. 왼쪽, 오른쪽 각각에서 분할 정복으로 최대 넓이를 구한다.
    // 3. 가운데를 기준으로 양쪽으로 확장하면서, 범위 내 최솟값을 유지하고 넓이를 계산한다.
    // 4. 세 가지 경우 중 최댓값을 반환한다.
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        while(c-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            //System.out.println(divideConquer(arr, 0, n - 1));
            System.out.println(solveStack(arr));
        }
    }

    // 분할 정복 풀이 방법
    private static int divideConquer(int[] arr, int start, int end) {

        if(start == end) {
            return arr[start];
        }

        int mid = (start + end) / 2;
        int leftMax = divideConquer(arr, start, mid); // 왼쪽 범위
        int rightMax = divideConquer(arr, mid + 15, end); // 오른쪽 범위

        int lo = mid;
        int hi = mid + 1;
        int minHeight = Math.min(arr[lo], arr[hi]);
        int midMax = minHeight * 2; // 밑의 너비 갯수
        // 확장
        while(start < lo || end > hi) {
            if(hi < end && (lo == start || arr[lo - 1] < arr[hi + 1])) { // 오른쪽만 확장 가능
                hi++;
                minHeight = Math.min(minHeight, arr[hi]);
            } else { // 왼쪽만 확장
                lo--;
                minHeight = Math.min(minHeight, arr[lo]);
            }

            midMax = Math.max(midMax, minHeight * (hi - lo + 1));
        }

        return Math.max(midMax, Math.max(leftMax, rightMax));
    }

    // 스택 풀이 방법
    private static int solveStack(int[] heights) {
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        int n = heights.length;

        for(int i = 0; i <= n; i++) {
            int h = (i == n ? 0 : heights[i]);

            while(!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.peek()];
                int width = stack.isEmpty() ? i : i - stack.peek();
                maxArea = Math.max(maxArea, height * width);
                stack.pop();
            }

            stack.push(i);
        }

        return maxArea;
    }
}
