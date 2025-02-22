import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        HashMap<String, Integer> map = new HashMap<>(); // 총 합계 구하기
        HashMap<String, PriorityQueue<int[]>> songs = new HashMap<>();

        for (int i = 0; i < plays.length; i++) { // O(n) = 10,000
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);

            songs.putIfAbsent(genres[i], new PriorityQueue<>((a, b) -> { // 키가 존재하지 않은 경우 작업
                if (b[0] == a[0]) { // 재생 횟수 같은 경우
                    return a[1] - b[1]; // 인덱스 오름 차순
                }
                return b[0] - a[0]; // 재생 횟수 내림차순
            }));

            // 배열 -> {재생횟수, 인덱스}
            songs.get(genres[i]).add(new int[]{plays[i], i});
        }

        List<String> sortedKeys = new ArrayList<>(map.keySet());
        sortedKeys.sort((o1, o2) -> map.get(o2) - map.get(o1)); // 내림 차순

        List<Integer> answer = new ArrayList<>();

        for (String genre : sortedKeys) {
            PriorityQueue<int[]> pq = songs.get(genre); // 우선순위 큐 -> 인덱스 기준
            for (int i = 0; i < 2 && !pq.isEmpty(); i++) {
                answer.add(pq.poll()[1]); // 인덱스 추가
            }
        }

        // intValue -> Integer를 int로 변환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}