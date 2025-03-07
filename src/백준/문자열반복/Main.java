package 백준.문자열반복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            String value = st.nextToken();
            String answer = "";

            for(int j = 0; j < value.length(); j++) {
                answer += String.valueOf(value.charAt(j)).repeat(a);
            }

            System.out.println(answer);
        }
    }
}
