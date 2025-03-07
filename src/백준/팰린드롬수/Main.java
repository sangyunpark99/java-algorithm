package 백준.팰린드롬수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String value = br.readLine();

            if(value.equals("0")) {
                break;
            }

            String reversed = new StringBuilder(value).reverse().toString();

            if(value.equals(reversed)) {
                System.out.println("yes");
            }else {
                System.out.println("no");
            }
        }
    }
}
