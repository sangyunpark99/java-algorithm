import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            LinkedList<Character> password = new LinkedList<>();
            ListIterator<Character> cursor = password.listIterator();

            String input = br.readLine();
            for (char ch : input.toCharArray()) {
                if (ch == '<') {
                    if (cursor.hasPrevious()) cursor.previous();
                } else if (ch == '>') {
                    if (cursor.hasNext()) cursor.next();
                } else if (ch == '-') {
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                        cursor.remove();
                    }
                } else {
                    cursor.add(ch);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (char c : password) {
                sb.append(c);
            }
            System.out.println(sb);
        }
    }
}