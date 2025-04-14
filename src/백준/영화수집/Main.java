package 백준.영화수집;

import java.io.*;
import java.util.*;

public class Main {
    static int[] tree = new int[200004];
    static Map<Integer, Integer> map = new HashMap<>();

    // 펜윅 트리 업데이트
    static void update(int idx, int value) {
        while (idx < tree.length) {
            tree[idx] += value;
            idx += (idx & -idx);
        }
    }

    // 펜윅 트리 구간합
    static int sum(int idx) {
        int ret = 0;
        while (idx > 0) {
            ret += tree[idx];
            idx -= (idx & -idx);
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());  // 테스트 케이스 수

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 영화 수
            int m = Integer.parseInt(st.nextToken()); // 요청 수

            Arrays.fill(tree, 0);
            map.clear();

            int updateIdx = 100001;

            // 초기 영화 위치 설정
            for (int i = 1; i <= n; i++) {
                update(i + updateIdx, 1);
                map.put(i, i + updateIdx);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int temp = Integer.parseInt(st.nextToken());

                int idx = map.get(temp);
                bw.write((sum(idx) - 1) + " "); // 현재 몇 번째에 있는지 출력

                update(idx, -1); // 기존 위치 제거
                update(--updateIdx, 1); // 맨 위에 추가
                map.put(temp, updateIdx); // 위치 갱신
            }

            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}