import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] switchs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        switchs = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            switchs[i] = Integer.parseInt(st.nextToken());
        }
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int switchNumber = Integer.parseInt(st.nextToken());

            if (s == 1) {
                male(switchNumber);
                continue;
            }

            female(switchNumber);
        }

        printResult();
        br.close();
    }

    private static void male(int number) {
        int i = 1;
        while (true) {
            if (number * i > switchs.length) {
                break;
            }
            switchs[(number * i) - 1] = turn(switchs[(number * i) - 1]);
            i++;
        }
    }

    private static void female(int number) {
        number -= 1;
        int left = number - 1;
        int right = number + 1;

        switchs[number] = turn(switchs[number]);

        while (left >= 0 && right < switchs.length && switchs[left] == switchs[right]) {
            switchs[left] = turn(switchs[left]);
            switchs[right] = turn(switchs[right]);
            left--;
            right++;
        }
    }

    private static int turn(int value) {
        return value == 1 ? 0 : 1;
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < switchs.length; i++) {
            sb.append(switchs[i]);
            sb.append(" ");
            if((i+1)%20 == 0) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}