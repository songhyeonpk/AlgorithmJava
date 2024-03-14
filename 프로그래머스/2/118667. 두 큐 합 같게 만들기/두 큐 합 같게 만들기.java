import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        long q1Sum = 0;
        for(int num : queue1) {
            q1Sum += num;
            q1.offer(num);
        }
        
        Queue<Integer> q2 = new LinkedList<>();
        long q2Sum = 0;
        for(int num : queue2) {
            q2Sum += num;
            q2.offer(num);
        }
        
        int answer = 0;
        long result = (q1Sum + q2Sum) / 2;
        while(true) {
            if(answer > (queue1.length + queue2.length) * 2) {
                answer = -1;
                break;
            }
            
            if(q1Sum == result && q2Sum == result) {
                break;
            }
            
            if(q1Sum > q2Sum) {
                int num = q1.poll();
                q2.offer(num);
                q1Sum -= num;
                q2Sum += num;
            } else {
                int num = q2.poll();
                q1.offer(num);
                q2Sum -= num;
                q1Sum += num;
            }
            answer++;
        }
        
        return answer;
    }
}