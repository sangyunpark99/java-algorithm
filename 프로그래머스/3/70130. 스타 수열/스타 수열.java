import java.util.*;


class Solution {
    public int solution(int[] a) {
        if (a.length < 2) return 0;
        
        int[] count = new int[500001];
        for (int num : a) {
            count[num]++;
        }

        int maxLength = 0;
        for (int num = 0; num < count.length; num++) {
            if (count[num] * 2 <= maxLength) continue;

            int length = 0;
            for (int i = 0; i < a.length - 1; i++) {
                if ((a[i] == num || a[i + 1] == num) && a[i] != a[i + 1]) {
                    length += 2;
                    i++;
                }
            }
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}    