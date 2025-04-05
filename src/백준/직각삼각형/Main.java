package 백준.직각삼각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0 && c == 0) {
                break;
            }

            int max = Math.max(a, Math.max(b,c));

            List<Integer> values = new ArrayList<>(List.of(a,b,c));

            values.remove((Integer)max);

            if(Math.sqrt(Math.pow(max,2)) == Math.sqrt(Math.pow(values.get(0),2) + Math.pow(values.get(1),2))) {
                System.out.println("right");
                continue;
            }

            System.out.println("wrong");
        }
    }
}
