import java.util.*;

class Solution {
    
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int answer = 0;
        for(int i = 0; i < enemy.length; i++) {
            int enemyCnt = enemy[i];
            
            // 무적권을 사용할 수 있는 경우 무적권 사용
            if(k > 0) {
                pq.add(enemyCnt);
                k -= 1;
                answer += 1;
                continue;
            }
            
            // 현재 라운드의 적의 수가 무적권을 사용한 적군의 수보다 많을 경우 교체
            if(!pq.isEmpty() && pq.peek() < enemyCnt) {
                enemyCnt = pq.poll();
                pq.add(enemy[i]);
            }
            
            // 남은 병사보다 적의 수가 많을 경우 종료
            if(n < enemyCnt) {
                break;
            }
            
            // 남은 병사에서 적의 수를 소모하고 다음 라운드 수행
            n -= enemyCnt;
            answer += 1;
        }
            
        return answer;
    }
    
}