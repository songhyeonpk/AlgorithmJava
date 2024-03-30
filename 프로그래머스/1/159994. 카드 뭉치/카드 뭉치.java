import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        List<String> cards1List = new ArrayList<>();
        for(int i = 0; i < cards1.length; i++) {
            cards1List.add(cards1[i]);
        }
        
        List<String> cards2List = new ArrayList<>();
        for(int i = 0; i < cards2.length; i++) {
            cards2List.add(cards2[i]);
        }
        
        String answer = "Yes";
        for(String str : goal) {
            if(cards1List.contains(str) && cards1List.get(0).equals(str)) {
                cards1List.remove(str);
                continue;
            }
            
            if(cards2List.contains(str) && cards2List.get(0).equals(str)) {
                cards2List.remove(str);
                continue;
            }
            
            answer = "No";
            break;
        }
        
        return answer;
        
    }
}