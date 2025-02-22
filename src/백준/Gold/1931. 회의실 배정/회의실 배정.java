import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 어떻게 최대한 많은 회의를 할까?
    // 빨리 시작하고 빨리 끝나는 회의를 먼저 한다.
    // 끝나는 시간을 기준으로 정렬을 하되, 끝나는 시간이 같은 경우엔 시작 시간을 기준으로 정렬한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        MeetingRoom[] meetingRooms = new MeetingRoom[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetingRooms[i] = new MeetingRoom(start, end);
        }

        Arrays.sort(meetingRooms, (o1, o2) -> {
            if (o1.endTime == o2.endTime) {
                return o1.startTime - o2.startTime;
            }
            return o1.endTime - o2.endTime;
        });

        int answer = 1;
        int bfEndTime = meetingRooms[0].endTime;

        for (int i = 1; i < meetingRooms.length; i++) {
            MeetingRoom cur = meetingRooms[i];
            int curEndTime = cur.endTime;
            int curStartTime = cur.startTime;

            if (bfEndTime <= curStartTime) {
                answer++;
                bfEndTime = curEndTime;
            }
        }

        System.out.println(answer);
    }

    public static class MeetingRoom {
        int startTime;
        int endTime;

        public MeetingRoom(int start, int end) {
            this.startTime = start;
            this.endTime = end;
        }
    }
}