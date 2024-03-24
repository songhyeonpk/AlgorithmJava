import java.util.*;

class Solution {
    static class Genre {
        String name;
        int totalPlayCnt;
        
        Genre(String name, int totalPlayCnt) {
            this.name = name;
            this.totalPlayCnt = totalPlayCnt;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Integer>> genreMap = new HashMap<>();
        Map<String, Integer> playMap = new HashMap<>();
        for(int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            
            if(!genreMap.containsKey(genre)) {
                genreMap.put(genre, new ArrayList<>());
            }
            
            genreMap.get(genre).add(i);
            playMap.put(genre, playMap.getOrDefault(genre, 0) + plays[i]);
        }
        
        List<Genre> list = new ArrayList<>();
        for(String key : playMap.keySet()) {
            list.add(new Genre(key, playMap.get(key)));
        }
        
        Collections.sort(list, (g1, g2) -> g2.totalPlayCnt - g1.totalPlayCnt);
        
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            String genre = list.get(i).name;
            
            List<Integer> indexList = genreMap.get(genre);
            if(indexList.size() == 1) {
                result.add(indexList.get(0));
                continue;
            }
            
            Collections.sort(indexList, (i1, i2) -> plays[i2] - plays[i1]);
            
            for(int j = 0; j < 2; j++) {
                result.add(indexList.get(j));
            }
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
        
    }
}