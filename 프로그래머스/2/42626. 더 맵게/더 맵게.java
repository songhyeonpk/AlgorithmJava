import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = mix(K, scoville);
        return answer;
    }
    
    public static int mix(int k, int[] scoville) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        });
        for(int num : scoville) {
            pq.offer(num);
        }
        
        int cnt = 0;
        while(!pq.isEmpty()) {
            if(pq.peek() >= k) {
                return cnt;
            }
            
            if(pq.size() <= 1 ) {
                return -1;
            }
            
            int firstNum = pq.poll();
            int secondNum = pq.poll();
            pq.offer(firstNum + (secondNum * 2));
            cnt++;
        }
        
        return -1;
    }
}