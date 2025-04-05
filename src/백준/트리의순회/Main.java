package 백준.트리의순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[] inOrder;
    private static int[] postOrder;
    private static int[] inIndex; // 중위 순회 인덱스 저장
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        inOrder = new int[n+1];
        postOrder = new int[n+1];
        inIndex = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());


        for(int i = 1; i <= n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        inIndex = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            inIndex[inOrder[i]] = i;
        }

        go(1,n,1,n);
        System.out.println(sb);
    }

    private static void go(int is, int ie, int ps, int pe) {
        if(ie < is || pe < ps) return;
        int root = postOrder[pe]; // 서브 트리의 루트 - 후위 순회의 마지막 원소
        int rootIdx = inIndex[root]; // 중위 순회에서 해당 루트의 인덱스 찾기 - 중위 순회 배열에서 루트 인덱스
        sb.append(root + " ");

        int len = rootIdx - is; // 왼쪽 트리

        // 루트 인덱스를 기준으로 왼쪽 서브트리, 오른쪽 서브트리 나누기
        go(is, rootIdx - 1, ps, ps + len - 1); // 왼쪽 서브 트리
        go(rootIdx + 1, ie, ps + len, pe - 1); // 오른쪽 서브 트리
    }
}