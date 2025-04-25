package 알고리즘문제해결전략1.chapter6.분할정복.팬미팅.정답풀이;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        while (c-- > 0) {
            char[] memberStr = br.readLine().toCharArray();
            char[] fanStr = br.readLine().toCharArray();

            int[] member = new int[memberStr.length];
            int[] fan = new int[fanStr.length];

            for (int i = 0; i < memberStr.length; i++) {
                member[i] = (memberStr[i] == 'M') ? 1 : 0;
            }
            for (int i = 0; i < fanStr.length; i++) {
                // 뒤집기
                fan[fanStr.length - i - 1] = (fanStr[i] == 'M') ? 1 : 0;
            }

            List<Integer> result = karatsuba(member, fan);

            int count = 0;
            for (int i = member.length - 1; i < fan.length; i++) {
                if (result.get(i) == 0) count++;
            }

            System.out.println(count);
        }
    }

    // Karatsuba 곱셈 구현
    public static List<Integer> karatsuba(int[] a, int[] b) {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        for (int n : a) A.add(n);
        for (int n : b) B.add(n);
        return karatsuba(A, B);
    }

    private static List<Integer> karatsuba(List<Integer> a, List<Integer> b) {
        int an = a.size(), bn = b.size();
        if (an < bn) return karatsuba(b, a);
        if (an == 0 || bn == 0) return new ArrayList<>();
        if (an <= 50) return multiply(a, b);

        int half = an / 2;

        List<Integer> a0 = a.subList(0, half);
        List<Integer> a1 = a.subList(half, an);
        List<Integer> b0 = b.subList(0, Math.min(bn, half));
        List<Integer> b1 = b.subList(Math.min(bn, half), bn);

        List<Integer> z0 = karatsuba(a0, b0);
        List<Integer> z2 = karatsuba(a1, b1);

        List<Integer> a0a1 = add(a0, a1);
        List<Integer> b0b1 = add(b0, b1);

        List<Integer> z1 = karatsuba(a0a1, b0b1);
        z1 = sub(z1, z0);
        z1 = sub(z1, z2);

        List<Integer> result = new ArrayList<>(Collections.nCopies(z0.size() + z1.size() + z2.size(), 0));

        addTo(result, z0, 0);
        addTo(result, z1, half);
        addTo(result, z2, half + half);

        return result;
    }

    // 기본 곱셈
    private static List<Integer> multiply(List<Integer> a, List<Integer> b) {
        List<Integer> c = new ArrayList<>(Collections.nCopies(a.size() + b.size(), 0));
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                c.set(i + j, c.get(i + j) + a.get(i) * b.get(j));
            }
        }
        return c;
    }

    // 두 벡터 더하기
    private static List<Integer> add(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        int n = Math.max(a.size(), b.size());
        for (int i = 0; i < n; i++) {
            int ai = (i < a.size()) ? a.get(i) : 0;
            int bi = (i < b.size()) ? b.get(i) : 0;
            result.add(ai + bi);
        }
        return result;
    }

    // 두 벡터 빼기
    private static List<Integer> sub(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>(a);
        for (int i = 0; i < b.size(); i++) {
            result.set(i, result.get(i) - b.get(i));
        }
        return result;
    }

    // c에 b를 k만큼 쉬프트하여 더함
    private static void addTo(List<Integer> a, List<Integer> b, int k) {
        for (int i = 0; i < b.size(); i++) {
            a.set(i + k, a.get(i + k) + b.get(i));
        }
    }
}