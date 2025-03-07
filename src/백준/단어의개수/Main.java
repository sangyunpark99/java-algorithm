package 백준.단어의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        String[] values = input.split(" ");

        int answer = 0;
        for(String value : values) {
            if(!value.isBlank()) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
