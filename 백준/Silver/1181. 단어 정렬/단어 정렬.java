import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<String> values = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            if(!values.contains(s)) {
                values.add(s);
            }
        }

        Collections.sort(values, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });

        for(String value: values) {
            System.out.println(value);
        }
    }
}