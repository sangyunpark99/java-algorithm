package 백준.철로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int d;
    private static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int house = Integer.parseInt(st.nextToken());
            int office = Integer.parseInt(st.nextToken());

            int start = Math.min(house, office);
            int end = Math.max(house, office);

            lines.add(new Line(start, end));
        }

        Collections.sort(lines, (o1, o2) -> o1.end - o2.end);

        d = Integer.parseInt(br.readLine());

        PriorityQueue<Integer>  pq = new PriorityQueue<>();
        int cnt = 0;
        for(int i = 0; i < lines.size(); i++) {
            Line cur = lines.get(i);
            int lineStart = cur.end - d;

            while(!pq.isEmpty() && pq.peek() < lineStart) { // 시작 지점보다 작으면 빼줍니다.
                pq.poll();
            }

            if(cur.start >= lineStart) {
                pq.offer(cur.start);
            }

            cnt = Math.max(cnt, pq.size());
        }

        System.out.println(cnt);
    }

    public static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
