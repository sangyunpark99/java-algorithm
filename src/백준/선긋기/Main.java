package 백준.선긋기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new Line(start, end));
        }

        lines.sort((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }

            return o1.start - o2.start; // 시작순 정렬
        });

        int start = lines.get(0).start;
        int end = lines.get(0).end;
        int result = 0;
        for(int i = 1; i < lines.size(); i++) {
            Line nextLine = lines.get(i);
            // 겹치는 경우 - 끝지점 갱신
            if(end >= nextLine.start && end < nextLine.end) {
                end = nextLine.end;
            } else if(end < nextLine.start) {
                // 겹치지 않는 경우 - 누적 길이 더하기
                result += (end - start);
                start = nextLine.start;
                end = nextLine.end;
            }
        }

        result += end - start;
        System.out.println(result);
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
