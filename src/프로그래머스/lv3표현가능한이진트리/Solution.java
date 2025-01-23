package 프로그래머스.lv3표현가능한이진트리;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new long[]{2, 1, 10000})));
    }

    private boolean isTree = false;

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String binary = convertBinary(numbers[i]);
            long depth = getBinaryDepth(binary.length());
            long totalNodeCount = (long) Math.pow(2, depth) - 1;

            // 포화 이진 트리 길이로 맞추기
            if (binary.length() < totalNodeCount) {
                StringBuilder padding = new StringBuilder();
                for (long j = 0; j < totalNodeCount - binary.length(); j++) {
                    padding.append("0");
                }
                binary = padding + binary;
            }

            // 이진 트리 조건 검사
            char[] fullBinaryTree = binary.toCharArray();
            answer[i] = validateBinaryTree(fullBinaryTree, 0, fullBinaryTree.length - 1) ? 1 : 0;
        }

        return answer;
    }

    private static boolean validateBinaryTree(char[] fullBinaryTree, int start, int end) {

        if (start >= end) {
            return true;
        }

        int mid = (start + end) / 2;
        char root = fullBinaryTree[mid];

        for (int i = mid - 1; i < mid; i++) {
            if (root == '0' && fullBinaryTree[i] == '1') {
                return false;
            }
        }

        for (int i = mid; i < end; i++) {
            if (root == '0' && fullBinaryTree[i] == '1') {
                return false;
            }
        }

        return validateBinaryTree(fullBinaryTree, start, mid - 1) && validateBinaryTree(fullBinaryTree, mid + 1, end);
    }

    private static String convertBinary(long number) {
        return Long.toBinaryString(number);
    }

    private static long getBinaryDepth(long length) {
        return (long) Math.ceil(Math.log(length + 1) / Math.log(2));
    }
}