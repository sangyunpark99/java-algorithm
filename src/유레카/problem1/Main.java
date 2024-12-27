package 유레카.problem1;

public class Main {
    public static void main(String[] args) {
        double a = (double) 1 / 9;

        double p = 0;
        double q = 1;
        boolean flag = true;
        while (true) {
            for (int i = 0; i <= q; i++) {
                if (i / q == a) {
                    p = i;
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                break;
            }

            q++;

            if (q == 100000) {
                break;
            }
        }

        System.out.println(p);
        System.out.println(q);
    }
}
