package 알고리즘문제해결전략1.chapter6.큐와스택데크.짝이맞지않는괄호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    private static int testCase;
    private static HashMap<Character, Character> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine());

        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        while(testCase-- > 0) {
            char[] values = br.readLine().toCharArray();
            answer(values);
        }
    }

    private static void answer(char[] values) {

        Stack<Character> stack = new Stack<>();

        for(char value: values) {
            if(stack.isEmpty()) {
                stack.push(value);
                continue;
            }

            if(value == ')' || value == ']' || value == '}') {
                if(!isSame(map.get(value), stack)) { // 짝이 맞는지
                    System.out.println("NO");
                    return;
                }
                continue;
            }

            stack.push(value);
        }

        if(stack.isEmpty()) {
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }
    }

    private static boolean isSame(char value, Stack<Character> stack) {
        if(stack.peek() == value) {
            stack.pop();
            return true;
        }

        return false;
    }
}
