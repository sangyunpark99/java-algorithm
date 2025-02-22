import java.util.*;

class Solution {
    
    private long[] viewers;
    private static final int HOUR_PER_SECOND = 3600;
    private static final int MINUTE_PER_SECOND = 60;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        answer = findBestAdStartTime(play_time, adv_time, logs);
        
        return answer;
    }
    
    public String findBestAdStartTime(String playTime, String advTime, String[] logs) {
        
        int playTimeToSeconds = convertSecond(playTime);
        int advTimeToSeconds = convertSecond(advTime);
        
        viewers = new long[playTimeToSeconds + 1];
        
        for(String log: logs) {
            String[] logSplit = log.split("-");
            int startTimeToSeconds = convertSecond(logSplit[0]);
            int endTimeToSeconds = convertSecond(logSplit[1]);
            viewers[startTimeToSeconds] += 1;
            viewers[endTimeToSeconds] -= 1;
        }
        
        // 1초 ~ 영상끝나는 시간까지
        for(int i = 1; i <= playTimeToSeconds; i++) { // 시청자 누적
            viewers[i] += viewers[i-1];
        }
        
        for(int i = 1; i <= playTimeToSeconds; i++) { // 시청시간 누적
            viewers[i] += viewers[i-1];
        }
        
        long maxViewTime = 0;
        long maxStartTime = 0;
        for(int start = 0; start <= playTimeToSeconds - advTimeToSeconds; start++) {
            int end = start + advTimeToSeconds - 1;
            long currentViewTime = viewers[end]  - (start > 0 ? viewers[start - 1] : 0);
            if(currentViewTime > maxViewTime) {
                maxViewTime = currentViewTime;
                maxStartTime = start; // 제일 먼저 시작
            }
        }
        
        return convertToTime(maxStartTime);
    }
    
    private String convertToTime(long time) {
        long hour = time / HOUR_PER_SECOND;
        long minute = time % HOUR_PER_SECOND / MINUTE_PER_SECOND;
        long second = time % HOUR_PER_SECOND % MINUTE_PER_SECOND;
        
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }
    
    public int convertSecond(String time) {
        int totalSeconds = 0;
        String[] splitTime = time.split(":");
        
        int hours = Integer.valueOf(splitTime[0]) * HOUR_PER_SECOND;
        int minutes = Integer.valueOf(splitTime[1]) * MINUTE_PER_SECOND;
        int seconds = Integer.valueOf(splitTime[2]);
        
        totalSeconds = hours + minutes + seconds;
        
        return totalSeconds;
    }
}