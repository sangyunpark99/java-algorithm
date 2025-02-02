package 프로그래머스.단어변환;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private int answer = 0;
    private boolean[] visited;
    private String Target;
    private String Begin;
    private String[] Words;

    public int solution(String begin, String target, String[] words) {

        Target = target;
        Begin = begin;
        Words = words;
        visited = new boolean[words.length];

        if (!isContainWord()) {
            return 0;
        }

        bfs();

        return answer;
    }

    private void bfs() {
        Queue<Word> queue = new LinkedList<>();
        queue.add(new Word(Begin, 0));

        while (!queue.isEmpty()) {
            Word curWord = queue.poll();
            String curText = curWord.text;
            int curCnt = curWord.cnt;

            if (curText.equals(Target)) {
                answer = curCnt - 1;
                return;
            }

            for (int i = 0; i < Words.length; i++) {
                String nextText = Words[i];
                if (!visited[i] && isNextText(curText, nextText)) {
                    visited[i] = true;
                    queue.add(new Word(nextText, curCnt));
                }
            }
        }
    }

    private boolean isNextText(String curText, String nextText) {

        int differCnt = 0;
        String[] splitCurText = curText.split("");
        String[] splitNextText = nextText.split("");

        for (int i = 0; i < splitCurText.length; i++) {
            if (!splitCurText[i].equals(splitNextText[i])) {
                differCnt++;
            }

            if (differCnt > 1) {
                return false;
            }
        }

        return true;
    }

    private boolean isContainWord() {
        for (String word : Words) {
            if (Target.equals(word)) {
                return true;
            }
        }

        return false;
    }

    class Word {
        String text;
        int cnt;

        Word(String text, int curCnt) {
            this.text = text;
            this.cnt = curCnt + 1;
        }
    }
}