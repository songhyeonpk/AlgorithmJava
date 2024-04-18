import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        List<String> dcList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            dcList.add(discount[i]);
        }
        
        int answer = 0;
        int day = 1;
        int end = 10;
        while(true) {
            int result = 0;
            for(int i = 0; i < want.length; i++) {
                int cnt = Collections.frequency(dcList, want[i]);
                if(cnt == number[i]) {
                    result += 1;
                }
            }
            
            if(result == want.length) {
                answer++;
            }
            
            end += 1;
            if(end > discount.length) {
                break;
            }
            
            dcList.remove(0);
            dcList.add(discount[end - 1]);
            day += 1;
        } 
        
        return answer;
    }
}