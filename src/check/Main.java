package check;

public class Main {
    public static void main(String[] args) {
        for(int mask = 0; mask < (1 << 10); mask++) {
            System.out.println(Integer.toBinaryString(mask));
        }
    }
}
