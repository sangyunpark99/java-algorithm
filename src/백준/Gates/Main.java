package 백준.Gates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 문제 유형 : greedy + union find
    private static int G;
    private static int P;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parent = new int[G + 1]; // 게이트 번호

        for(int i = 1; i <= G; i++) { // 자기 자신
            parent[i] = i;
        }

        int answer = 0;
        for(int i = 0; i < P; i++) {
            int airplaneNumber = Integer.parseInt(br.readLine());
            int dockingGate = find(airplaneNumber); // docking할 게이트 찾기

            if(dockingGate == 0) { // 게이트가 전부 차있다.
                break;
            }

            answer++;
            union(dockingGate); // 담을 수 있는 게이트 찾기
        }

        System.out.println(answer);
    }

    private static int find(int gateNumber) {
        if(gateNumber == parent[gateNumber]) { // 자기 자신인 경우 - 게이트가 비어 있음
            return parent[gateNumber];
        }

        return parent[gateNumber] = find(parent[gateNumber]);
    }

    private static void union(int gateNumber) { // 도킹
        parent[gateNumber] = find(gateNumber - 1); // 한단계 작은 게이트
    }
}