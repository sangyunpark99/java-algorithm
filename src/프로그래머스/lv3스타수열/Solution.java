package 프로그래머스.lv3스타수열;

public class Solution {
    public int solution(int[] a) {
        if (a.length < 2) {
            return 0; // 스타수열을 만들 수 없는 경우
        }

        int[] count = new int[500001]; // 각 숫자의 등장 횟수
        for (int num : a) {
            count[num]++;
        }

        int maxLength = 0;
        for (int num = 0; num < count.length; num++) {
            if (count[num] * 2 <= maxLength) {
                continue; // 더 긴 스타수열 생성 불가능
            }

            int length = 0;
            for (int i = 0; i < a.length - 1; i++) {
                if ((a[i] == num || a[i + 1] == num) && a[i] != a[i + 1]) {
                    length += 2; // 유효한 쌍 발견
                    i++; // 다음 인덱스 건너뜀
                }
            }
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}
