package 백준.설탕배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n == 3) {
            System.out.println(1);
            return;
        }

        if(n == 4 || n == 7) {
            System.out.println(-1);
            return;
        }

        if(n % 5 == 0) {
            System.out.println(n / 5);
        }else {
            int cnt = n / 5;
            if(n % 5 == 3) {
                cnt++;
                System.out.println(cnt);
            }else if(n % 5 == 2) {
                cnt+=2;
                System.out.println(cnt);
            } else {
                cnt--;
                cnt += (5 + n % 5) / 3;
                System.out.println(cnt);
            }
        }
    }
}