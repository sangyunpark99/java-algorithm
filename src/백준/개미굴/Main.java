package 백준.개미굴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String[] path = new String[k];
            for(int j = 0; j < k; j++) {
                path[j] = st.nextToken();
            }

            trie.insert(path);
        }

        trie.print(trie.root, 0);
    }

    public static class Node {
        TreeMap<String, Node> children = new TreeMap<>();
    }

    static class Trie {
        Node root = new Node();

        void insert(String[] path) {
            Node cur = root;
            for(String food: path) {
                cur.children.putIfAbsent(food, new Node());
                cur = cur.children.get(food);
            }
        }

        void print(Node cur, int depth) {
            for(String key : cur.children.keySet()) {
                System.out.println("--".repeat(depth) + key);
                print(cur.children.get(key), depth + 1);
            }
        }
    }
}
