package 백준.검증수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        while(st.hasMoreTokens()) {
            int value = Integer.parseInt(st.nextToken());
            answer += Math.pow(value,2);
        }

        System.out.println(answer % 10);
    }
}
