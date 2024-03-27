import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(String str : operations) {
            String operation = str.split(" ")[0];
            int num = Integer.parseInt(str.split(" ")[1]);
            
            if(operation.equals("I")) {
                minQueue.offer(num);
                maxQueue.offer(num);
                continue;
            }
            
            if(num == -1 && !minQueue.isEmpty()) {
                int minNum = minQueue.poll();
                maxQueue.remove(minNum);
            }
            
            if(num == 1 && !maxQueue.isEmpty()) {
                int maxNum = maxQueue.poll();
                minQueue.remove(maxNum);
            }
        }
        
        int[] answer = new int[2];
        if(minQueue.isEmpty() && maxQueue.isEmpty()) {
            return answer;
        }
        
        answer[0] = maxQueue.poll();
        answer[1] = minQueue.poll();
        return answer;
    }
}