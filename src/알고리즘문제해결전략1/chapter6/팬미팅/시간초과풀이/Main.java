package 알고리즘문제해결전략1.chapter6.팬미팅.시간초과풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String[] member;
    private static String[] fan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());

        while(c-- > 0) {
            member = br.readLine().split("");
            fan = br.readLine().split("");

            // Sliding Window - 팬(멤버의 수만큼)
            int left = 0;
            int right = member.length - 1;

            int answer = 0;

            while(left <= fan.length - member.length) {
                if(divideConquer(left, right, 0, member.length - 1)) {
                    answer++;
                }

                left++;
                right++;
            }

            System.out.println(answer);
        }
    }

    private static boolean divideConquer(int fanStart, int fanEnd, int memberStart, int memberEnd) {

        if(memberStart == memberEnd && fanStart == fanEnd) {
           if("M".equals(member[memberStart]) && "M".equals(fan[fanStart])) { // 둘다 남자일경우
               return false;
           }
           return true;
        }

        int midFan = (fanStart + fanEnd) / 2;
        int midMember = (memberStart + memberEnd) / 2;
        boolean left = divideConquer(fanStart, midFan, memberStart, midMember);
        boolean right = divideConquer(midFan + 1, fanEnd, midMember + 1, memberEnd);

        return left && right;
    }
}
