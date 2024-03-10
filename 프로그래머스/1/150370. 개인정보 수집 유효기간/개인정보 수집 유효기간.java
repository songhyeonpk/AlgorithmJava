import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int ty = Integer.parseInt(today.split("\\.")[0]);
        int tm = Integer.parseInt(today.split("\\.")[1]);
        int td = Integer.parseInt(today.split("\\.")[2]);
        
        Map<String, Integer> termsMap = new HashMap<>();
        for(String term : terms) {
            String[] termArr = term.split(" ");
            termsMap.put(termArr[0], Integer.parseInt(termArr[1]));
        }
        
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < privacies.length; i++) {
            String[] privacyArr = privacies[i].split(" ");
            int y = Integer.parseInt(privacyArr[0].split("\\.")[0]);
            int m = Integer.parseInt(privacyArr[0].split("\\.")[1]);
            int d = Integer.parseInt(privacyArr[0].split("\\.")[2]);
            String k = privacyArr[1];
            
            int term = termsMap.get(k);
            int ey = y + ((m + term) / 12);
            int em = (m + term) % 12;
            int ed = d - 1;
            
            if(ed == 0) {
                em -= 1;
                ed = 28;
            }
            
            if(em == 0) {
                ey -= 1;
                em = 12;
            }
            
            if(ey < ty || (ey == ty && em < tm) || (ey == ty && em == tm && ed < td)) {
                result.add(i + 1);
            }
            
        }
        
        Collections.sort(result);
        
        int[] answer = new int[result.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}