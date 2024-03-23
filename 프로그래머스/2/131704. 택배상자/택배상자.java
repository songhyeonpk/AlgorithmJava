import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int boxNum = 1;
        Stack<Integer> container = new Stack<>();
        for(int i = 0; i < order.length; i++) {
            int num = order[i];
            
            if(num == boxNum) {
                answer++;
                boxNum++;
            } else if(num > boxNum) {
                for(int j = boxNum; j < num; j++) {
                    container.push(j);
                }
                boxNum = num + 1;
                answer++;
            } else {
                if(container.isEmpty() || container.get(container.size() - 1) != num) {
                    break;
                }
                answer++;
                container.pop();
            }
        }
        
        return answer;
    }
}