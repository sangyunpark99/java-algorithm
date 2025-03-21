package 백준.카드1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        List<Integer> cards = new ArrayList<>();

        while(cards.size() != N) {
            cards.add(queue.poll());
            queue.offer(queue.poll());
        }

        for(int i = 0; i < cards.size(); i++) {
            System.out.print(cards.get(i) + " ");
        }
    }
 }
