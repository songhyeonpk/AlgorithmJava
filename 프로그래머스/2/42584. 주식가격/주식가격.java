import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        for(int i = prices.length - 1; i >= 0; i--) {
            stack.push(prices[i]);
        }
        
        int[] answer = new int[prices.length];
        int index = 0;
        while(index < prices.length) {
            int price = stack.pop();
            
            int time = 0;
            for(int i = stack.size() - 1; i >= 0; i--) {
                if(price > stack.get(i)) {
                    time += 1;
                    break;
                }
                time++;
            }
            
            answer[index++] = time;
        }
        
        return answer;
    }
}