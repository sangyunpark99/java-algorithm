import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        int bfn = n;
        
        // 제일 먼저 타야 하는 경우
        int[] queue = new int[timetable.length];
        
        for(int i = 0; i < timetable.length; i++) {
            queue[i] = convertMinute(timetable[i]);
        }
        
        Arrays.sort(queue);
        
        int busTime = convertMinute("09:00");
        int idx = 0;
        int overBus = 0;
        
        while(n > 0) {
            overBus = 0; // 버스에 탄 사람
            while(overBus < m && idx < queue.length && busTime >= queue[idx]) { 
                idx++; // 다음 사람
                overBus++; // 버스 탑승
            }
            
            busTime += t; // 버스 탈 시간
            n--; // 버스 이동
        }
                
        if(idx > 0 && m - overBus > 0) { // 버스에 탈 자리가 있는 경우
            return convertHour(Math.max(convertMinute("09:00"), busTime - t));
        }
        
        if(idx > 0 && m - overBus == 0) { // 버스에 탈 자리가 없는 경우 -> 마지막에 탄 승객보다 1분 빠르게 탑승
            return convertHour(queue[idx - 1] - 1);
        }
        
        if(idx == 0) { // 한명도 못탄 경우
            return convertHour(convertMinute("09:00") + (bfn - 1) * t);
        }
        
        return answer;
    }
    
    private String convertHour(int totalMinute) {
        int hour = totalMinute / 60;
        int minute = totalMinute % 60;
        
        String hourValue = hour < 10 ? "0" + hour : hour + "";
        String minuteValue = minute < 10 ? "0" + minute : minute + "";
        
        return hourValue + ":" + minuteValue;
    }
    
    private int convertMinute(String time) {
        String[] splitTime = time.split(":");
        int hour = Integer.parseInt(splitTime[0]) * 60;
        int minute = Integer.parseInt(splitTime[1]);
        
        return hour + minute;
    }
}