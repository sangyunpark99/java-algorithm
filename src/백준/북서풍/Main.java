package 백준.북서풍;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int x, y; // x=가로, y=세로로 저장

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringTokenizer st;
        while(t-->0){
            int n = Integer.parseInt(br.readLine()); // 섬 갯수

            List<Point> p = new ArrayList<>();
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                p.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            Collections.sort(p, (a, b) -> b.y-a.y); // 내립 차순 정렬

            // y좌표 압축
            int[] tmp = new int[n];
            int cnt =1;
            for(int i=0; i<n; i++) {
                if(i>0 && p.get(i).y != p.get(i-1).y) cnt++;
                tmp[i] = cnt;
            }

            for(int i=0; i<n; i++) {
                p.get(i).y = tmp[i]; // y 값을 압축된 값으로 대체
            }

            // x 오름차순, 같은 x일 땐, y 오름차순 정렬
            Collections.sort(p, (a,b) -> {
                if(a.x == b.x) return a.y - b.y;
                else return a.x-b.x;
            });

            // 세그먼트 트리로 북서쪽 섬 개수 누적
            tree = new int[4*n];
            long res = 0;
            for(int i=0; i<n; i++) {
                res += query(1, n, 1, 1, p.get(i).y);
                update(1, n, 1, p.get(i).y);
            }
            System.out.println(res);
        }
    }

    // update: 해당 y 좌표에 섬이 하나 등장했음을 기록
    static void update(int s, int e, int node, int idx) {
        if(idx < s || e < idx) return;
        tree[node] += 1;
        if(s==e) return;
        int mid = (s+e) / 2;
        update(s, mid, node*2, idx);
        update(mid + 1, e, node * 2 + 1, idx);
    }

    // 1 ~ 현재 y 좌표까지 등장한 섬의 개수 합산
    static int query(int s, int e, int node, int l, int r) {
        if(e < l || r < s) return 0;
        if(l == s && e <= r) return tree[node];
        int mid = (s+e) / 2;
        return query(s, mid, node * 2, l, r) + query(mid + 1, e, node*2+1,l,r);
    }
}
