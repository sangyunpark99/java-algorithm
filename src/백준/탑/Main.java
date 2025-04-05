package 백준.탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    // 각가의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는가?

    private static int N;
    private static int[] arr;
    private static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Stack<int[]> firstStack = new Stack<>();
        for(int i = 0; i < arr.length; i++){
            firstStack.add(new int[]{i, arr[i]});
        }

        Stack<int[]> secondStack = new Stack<>();

        for(int i = 0; i <= arr.length; i++) {
            answer.add(0);
        }

        while(!firstStack.isEmpty()) {

            int[] firstPopValue = firstStack.pop();

            if(secondStack.isEmpty()) {
                secondStack.add(firstPopValue);
                continue;
            }

            while(!secondStack.isEmpty()) {
                int[] secondPopValue = secondStack.peek();

                if (firstPopValue[1] >= secondPopValue[1]) {
                    secondStack.pop();
                    answer.set(secondPopValue[0] + 1, firstPopValue[0] + 1); // index
                }else {
                    break;
                }
            }

            secondStack.add(firstPopValue); // 값 넣어 주기
        }

        for(int i = 1; i <= N; i++) {
            System.out.print(answer.get(i) + " ");
        }
    }
}
