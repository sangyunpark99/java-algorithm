import java.util.*;

// 1 ~ n
// 출입구, 입구, 쉼터, 산봉우리
// 각 지점은 양방향 통행이 가능한 등산로로 서로 다른 지점을 이동할 때 이 등산로를 이용해야 한다.
// 등산로별로 이동하는데 일정시간이 소요된다.
// 1-2-3-2-1 : 1번 출발, 2번, 3번, 2번, 1번 지점 순서대로 방문
// 쉼터 or 산봉우리 방문시 휴식을 취할 수 있고, 휴식 없이 이동해야 하는 시간 중 가장 긴 시간을 해당 등산 코스의 intensity라고 부릅니다.
// 출입구 중 한 곳에서 출발해 산봉우리 중 한 곳만 방문한 뒤 다시 원래 출입구로 돌아오는 등산코스를 정하려고 합니다.
// 출입구는 처음과 끝에 한번씩, 산봉우리는 한 번만 포함되어야 합니다.

// 뭘 구해야해? intensity가 최소가 되도록 하는 등산코스
// intensity는 휴식 없이 이동해야 하는 시간 중 가장 긴 시간
// intensity가 최소가 될려면 어떻게 해야할까?
// 출입구에서 등산로까지 최단 경로 + 등산로에서 원래 출입구로 돌아오는 최단 경로
// 휴식 없이 이동해야 하는 이동 시간
// 산봉우리를 만난 경우 더이상 경로 탐색을 할 필요가 없다. 이미 왔던 길을 되돌아가기 때문에

class Solution {
    
    private List<List<long[]>> graph = new ArrayList<>();
    private boolean[] isGate;
    private boolean[] isSummit;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];
        
        for (int gate : gates) isGate[gate] = true;
        for (int summit : summits) isSummit[summit] = true;
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] path : paths) {
            int start = path[0];
            int end = path[1];
            int weight = path[2];

            // 산봉우리를 지나친 경로는 양방향 연결 제한
            if (isGate[start] || isSummit[end]) {
                graph.get(start).add(new long[]{end, weight});
            } else if (isGate[end] || isSummit[start]) {
                graph.get(end).add(new long[]{start, weight});
            } else {
                graph.get(start).add(new long[]{end, weight});
                graph.get(end).add(new long[]{start, weight});
            }
        }

        return multiStartDijkstra(n, gates, summits);
    }
    
    private int[] multiStartDijkstra(int n, int[] gates, int[] summits) {
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        long[] intensity = new long[n + 1];
        Arrays.fill(intensity, Long.MAX_VALUE);

        // 출입구를 시작점으로 추가
        for (int gate : gates) {
            pq.offer(new long[]{gate, 0}); // {현재 위치, 현재 intensity}
            intensity[gate] = 0;
        }

        int minSummit = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            int now = (int) current[0];
            long nowIntensity = current[1];

            // 현재 intensity가 이미 기록된 intensity보다 크면 스킵
            if (nowIntensity > intensity[now]) continue;

            for (long[] neighbor : graph.get(now)) {
                int next = (int) neighbor[0];
                long weight = neighbor[1];

                // 다음 노드로 이동할 때의 최대 intensity 계산
                long newIntensity = Math.max(nowIntensity, weight);

                // 산봉우리인 경우 처리
                if (isSummit[next]) {
                    if (newIntensity < minIntensity || (newIntensity == minIntensity && next < minSummit)) {
                        minSummit = next;
                        minIntensity = (int) newIntensity;
                    }
                }

                // 다음 노드가 산봉우리가 아니고 더 작은 intensity로 이동 가능하면 업데이트
                if (!isSummit[next] && newIntensity < intensity[next]) {
                    intensity[next] = newIntensity;
                    pq.offer(new long[]{next, newIntensity});
                }
            }
        }

        return new int[]{minSummit, minIntensity};
    }
}