import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> strMap = new HashMap<>();
        int mapIdx = 1;
        for(int i = 0; i < 26; i++) {
            strMap.put((char)('A' + i) + "", mapIdx++);
        }
        
        List<Integer> result = new ArrayList<>();
        int idx = 0;
        while(idx < msg.length()) {
            String w = msg.charAt(idx) + "";
            String c = "";
            while(true) {
                idx += 1;
                if(idx >= msg.length()) {
                    break;
                }
                
                c = msg.charAt(idx) + "";
                if(!strMap.containsKey(w + c)) {
                    break;
                }
                
                w += c;
            }
            
            result.add(strMap.get(w));
            strMap.put(w + c, mapIdx++);
        }
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}