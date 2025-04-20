package 프로그래머스.자동완성;

import java.util.*;

class Solution {
    // 앞부분이 같은 경우 다른 문자가 나올때까자
    // 몇개의 문자를 입력하면 되는가?
    // 트라이 -> 단어의 공통 접두어를 빠르게 탐색
    public int solution(String[] words) {

        Trie trie = new Trie();

        for(String word: words) {
            trie.insert(word);
        }

        int answer = 0;

        for(String word: words) {
            answer += trie.getMinimumInputCount(word);
        }

        return answer;
    }

    class Node {
        Map<Character, Node> children = new HashMap<>();
        int count = 0; // 해당 노드를 지나간 단어 수
    }

    class Trie {

        Node root = new Node();

        public void insert(String word) {
            Node curr = root;
            for(char ch: word.toCharArray()) {
                curr.children.putIfAbsent(ch, new Node());
                curr = curr.children.get(ch);
                curr.count++;
            }
        }

        public int getMinimumInputCount(String word) {
            Node curr = root;
            int inputCount = 0;

            for(char ch: word.toCharArray()) {
                curr = curr.children.get(ch);
                inputCount++;
                if(curr.count == 1) break;
            }

            return inputCount;
        }
    }
}
