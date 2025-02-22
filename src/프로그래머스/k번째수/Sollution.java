package 프로그래머스.k번째수;

import java.util.Arrays;

public class Sollution {
    public static int[] solution(int[] array, int[][] commands){
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int[] sliceArray = new int[] {};

            int startIndex = commands[i][0]-1;
            int endIndex = commands[i][1];
            int kNum = commands[i][2];
            sliceArray = Arrays.copyOfRange(array,startIndex,endIndex); // 배열 자르기

            //자른 배열 정렬하기
            Arrays.sort(sliceArray);

            answer[i] = sliceArray[kNum-1];
        }
        return answer;
    }
}
