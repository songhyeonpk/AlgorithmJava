import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> pMap = new HashMap<>();
        for(String name : participant) {
            pMap.put(name, pMap.getOrDefault(name, 0) + 1);
        }
        
        for(String name : completion) {
            pMap.put(name, pMap.get(name) - 1);
        }
        
        String answer = "";
        for(Map.Entry<String, Integer> entry : pMap.entrySet()) {
            if(entry.getValue() > 0) {
                answer = entry.getKey();
                break;
            }
        }
        
        return answer;
    }
}