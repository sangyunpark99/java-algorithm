package 프로그래머스.호텔대실;

import java.util.*;

class Solution {
    // 최소한의 객실만 사용
    // 한 번 사용한 객실 -> 퇴실 시간을 기준으로 10분간 청소 후, 다음 손님 이용 : 퇴실 시간 + 10분
    // 필요한 최소 객실의 수

    // 1. book_time을 끝나는 시간 기준으로 정렬
    // 객실의 퇴실 시간과 입실 시간이 겹치지 않은 경우 같은 방 사용
    // 우선순위 큐를 사용해서 끝나는 시간 기준으로 정렬을 해서 넣어준다.
    // book_time을 입력 받을때마다 peek를 사용해서 끝나는 시간 + 10과 다음 시작시간을 비교해서, 다음 시작 시간이 더 큰경우 방 생성

    public int solution(String[][] book_time) {
        int answer = 0;

        Arrays.sort(book_time, (o1, o2) -> o1[0].compareTo(o2[0])); // 시작 시간 기준 정렬

        PriorityQueue<int[]> room = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        String[] first = book_time[0];
        String[] hourValue = first[0].split(":");
        String[] minuteValue = first[1].split(":");
        int firstBookStartTime = convert(Integer.parseInt(hourValue[0]), Integer.parseInt(hourValue[1]));
        int firstBookFinishTime = convert(Integer.parseInt(minuteValue[0]), Integer.parseInt(minuteValue[1]));

        room.offer(new int[]{firstBookStartTime, firstBookFinishTime});
        answer = room.size();

        for(int i = 1; i < book_time.length; i++) {
            String[] curStart = book_time[i][0].split(":");
            String[] curEnd = book_time[i][1].split(":");

            int curStartTime = convert(Integer.parseInt(curStart[0]), Integer.parseInt(curStart[1])); // 시작 시간
            int curEndTime = convert(Integer.parseInt(curEnd[0]), Integer.parseInt(curEnd[1])); // 끝나는 시간

            while(!room.isEmpty() && room.peek()[1] + 10 <= curStartTime) {
                room.poll();
            }
            room.offer(new int[]{curStartTime,curEndTime});
            answer = Math.max(answer,room.size());
        }

        return answer;
    }

    public int convert(int hour, int minute) { // 분 기준으로
        return hour * 60 + minute;
    }

}