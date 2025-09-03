import java.util.*;

class Solution {
    
    private HashMap<String, Integer> genreMap = new HashMap<>();
    private HashMap<String, List<int[]>> playMap = new HashMap<>();
    
    public int[] solution(String[] genres, int[] plays) {
        
        for(int i = 0; i < genres.length; i++) { // O(n)
            String genre = genres[i];
            int playCount = plays[i];
            
            if(genreMap.containsKey(genre)) { // 장르가 이미 포함되어 있는 경우
                genreMap.put(genre, genreMap.get(genre) + playCount);
                playMap.get(genre).add(new int[]{i, playCount});
                continue;
            }
            
            genreMap.put(genre, playCount);
            playMap.put(genre, new ArrayList<>());
            playMap.get(genre).add(new int[]{i, playCount});
            
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(genreMap.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue())); // O(logn)
        List<Integer> answer = new ArrayList<>();
        
        for(Map.Entry<String, Integer> entry: list) { // O(99)
            String genre = entry.getKey();
            List<int[]> genrePlays = playMap.get(genre);
            Collections.sort(genrePlays, new Comparator<int[]>() { // O(nlogn)
                @Override
                public int compare(int[] a, int[] b) {
                    if(a[1] == b[1]) {
                        return Integer.compare(a[0], b[0]);
                    }
                    
                    return Integer.compare(b[1], a[1]);
                }
            });
            
            for(int i = 0; i < genrePlays.size(); i++) {
                if(i > 1) {
                    break;
                }
                int[] genrePlay = genrePlays.get(i);
                answer.add(genrePlay[0]);
            }
        } // O(nlogn)
        
        int[] result = new int[answer.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = answer.get(i);
        }
        
        return result;
    }
}