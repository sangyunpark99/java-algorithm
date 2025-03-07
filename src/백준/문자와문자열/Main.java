package 백준.문자와문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String value = br.readLine();

        int n = Integer.parseInt(br.readLine());

        System.out.println(value.charAt(n - 1));
    }
}
