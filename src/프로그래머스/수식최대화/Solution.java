package 프로그래머스.수식최대화;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    private long answer = Integer.MIN_VALUE;
    private List<Node> expressionNode = new ArrayList<>();
    private List<Long> numbers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(new Solution().solution("50*6-3*2"));
    }

    public long solution(String expression) {

        String number = "";
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))) { // 숫자인경우
                number += expression.charAt(i);
            } else { // 숫자가 아닌 경우
                expressionNode.add(new Node(String.valueOf(expression.charAt(i)), i));
                numbers.add(Long.parseLong(number));
                number = "";
            }

            if (i == expression.length() - 1) {
                numbers.add(Long.parseLong(number));
            }
        }

        // 연산자 추출
        Set<String> expressionSet = new HashSet<>();
        for (int i = 0; i < expressionNode.size(); i++) {
            expressionSet.add(expressionNode.get(i).expression);
        }

        // 우선순위 부여
        priority(expressionSet.toArray(new String[]{}), 0, expressionSet.size(), expressionSet.size());

        // 최댓값을 찾는다.

        return answer;
    }

    class Node {
        String expression;
        int index;

        Node(String expression, int index) {
            this.expression = expression;
            this.index = index;
        }
    }

    public void priority(String[] expressions, int depth, int n, int r) {
        if (depth == r) {
            // 계산
            answer = Math.max(answer, totalCalculate(expressions));
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(expressions, depth, i);
            priority(expressions, depth + 1, n, r);
            swap(expressions, depth, i);
        }
    }

    public void swap(String[] expressions, int depth, int i) {
        String temp = expressions[depth];
        expressions[depth] = expressions[i];
        expressions[i] = temp;
    }

    public long totalCalculate(String[] expressions) {

        long totalValue = 0;
        List<Node> expressionNodeCopy = new ArrayList<>();
        List<Long> numbersCopy = new ArrayList<>();
        for (int i = 0; i < expressionNode.size(); i++) {
            expressionNodeCopy.add(expressionNode.get(i));
        }

        for (int i = 0; i < numbers.size(); i++) {
            numbersCopy.add(numbers.get(i));
        }

        for (int i = 0; i < expressions.length; i++) {
            String exp = expressions[i];
            for (int j = 0; j < expressionNodeCopy.size(); j++) {
                if (exp.equals(expressionNodeCopy.get(j).expression)) {
                    if (exp.equals("+")) {
                        totalValue = numbersCopy.remove(j) + numbersCopy.remove(j);
                    } else if (exp.equals("-")) {
                        totalValue = numbersCopy.remove(j) - numbersCopy.remove(j);
                    } else {
                        totalValue = numbersCopy.remove(j) * numbersCopy.remove(j);
                    }
                    numbersCopy.add(j, totalValue);
                    expressionNodeCopy.remove(j);
                    j--;
                }
            }
        }

        return Math.abs(totalValue);
    }
}
