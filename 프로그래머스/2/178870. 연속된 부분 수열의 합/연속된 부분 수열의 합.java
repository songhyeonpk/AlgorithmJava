import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int sectionLen = Integer.MAX_VALUE;
        int sectionFirst = 0;
        int sectionLast = 0;
        int start = 0;
        int end = 0;
        int sum = 0;
        while(end < sequence.length) {
            sum += sequence[end];
            
            // 합이 k 보다 크고 시작점이 끝점보다 작으면
            // 합이 k와 같거나 작을 때까지 시작점을 증가시키면서 합에서 값 감소
            while(sum > k && start <= end) {
                sum -= sequence[start++];
            }
            
            if(sum == k) {
                int diff = (end - start) + 1;
                if(diff < sectionLen) {
                    sectionLen = diff;
                    sectionFirst = start;
                    sectionLast = end;
                }
                
                if(diff == sectionLen) {
                    sectionFirst = Math.min(sectionFirst, start);
                    sectionLast = Math.min(sectionLast, end);
                }
            }
            
            end += 1;
        }
        
        int[] answer = {sectionFirst, sectionLast};
        return answer;
    }
}