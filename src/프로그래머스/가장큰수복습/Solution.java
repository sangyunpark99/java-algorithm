package 프로그래머스.가장큰수복습;

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";

        // 가장 큰 수를 만들기 위한 조건
        // 맨 앞의 수가 제일 큰 수를 맨 앞에 놓는다.
        // 자릿수가 2개 이상인 경우
        String[] numStrings = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        // b + a가 더 큰경우 b가 a보다 앞에 와야 한다.
        // a + b가 더 큰경우 a가 b보다 앞에 와야 한다.
        Arrays.sort(numStrings, (a,b) -> (b + a).compareTo(a + b));

        answer = String.join("",numStrings);

        if(numStrings[0].equals("0")) { // 0인 경우는 0
            answer = "0";
        }

        return answer;
    }
}
