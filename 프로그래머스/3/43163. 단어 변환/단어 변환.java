import java.util.*;

class Solution {
    // begin -> target으로 변환하는 가장 짧은 변환 과정
    // 한번에 한개만
    private int answer = 0;
    private boolean[] visited;
    private String Target;
    private String Begin;
    private String[] Words;
    
    public int solution(String begin, String target, String[] words) {
        
        // O(n) = 50 * 10
        Target = target;
        Begin = begin;
        Words = words;
        visited = new boolean[words.length];
        
        boolean isInWord = false;
        for(String word: Words) {
            if(target.equals(word)) {
                isInWord = true;
                break;
            }
        }
        
        if(!isInWord) {
            return 0;
        }
        
        bfs();
        
        return answer > 0 ? answer : 0;
    }
    
    private void bfs() {
        Queue<Word> queue = new LinkedList<>();
        
        queue.add(new Word(Begin, 0));
        
        while(!queue.isEmpty()) {
            Word curWord = queue.poll();
            String curText = curWord.text;
            int curCnt = curWord.cnt;
            
            if(curText.equals(Target)) {
                answer = curCnt - 1;
                return;
            }
            
            for(int i = 0; i < Words.length; i++) {
                String nextText = Words[i];
                if(!visited[i] && isNextText(curText, nextText)) {
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
        
        for(int i = 0; i < splitCurText.length; i++) {
            if(!splitCurText[i].equals(splitNextText[i])) {
                differCnt++;
            }
        }
        
        return differCnt == 1 ? true : false;
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