import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        
        int answer = Integer.MIN_VALUE;
        for(int i = 0; i < citations.length; i++) {
            answer = Math.max(answer, Math.min(citations[i], citations.length - i));
        }
        
        return answer;
    }
}