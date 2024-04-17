import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        List<Map<Character, Integer>> keyList = new ArrayList<>();
        for(String key : keymap) {
            char[] arr = key.toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < arr.length; i++) {
                if(!map.containsKey(arr[i])) {
                    map.put(arr[i], i + 1);
                }
            }
            
            keyList.add(map);
        }
        
        int[] answer = new int[targets.length];
        for(int i = 0; i < targets.length; i++) {
            char[] arr = targets[i].toCharArray();
            
            int totalCnt = 0;
            for(int j = 0; j < arr.length; j++) {
                char ch = arr[j];
                int cnt = Integer.MAX_VALUE;
                
                for(int k = 0; k < keyList.size(); k++) {
                    Map<Character, Integer> map = keyList.get(k);
                    if(map.containsKey(ch)) {
                        cnt = Math.min(cnt, map.get(ch));
                    }
                }
                
                if(cnt == Integer.MAX_VALUE) {
                    totalCnt = -1;
                    break;
                }
                
                totalCnt += cnt;
            }
            
            answer[i] = totalCnt;
        }
        
        return answer;
    }
}