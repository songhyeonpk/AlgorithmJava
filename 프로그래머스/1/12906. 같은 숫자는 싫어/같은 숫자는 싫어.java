import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for(int i = arr.length - 1; i >= 0; i--) {
            if(stack.isEmpty() || stack.get(stack.size() - 1) != arr[i]) {
                stack.push(arr[i]);
            }
        }
        
        int[] answer = new int[stack.size()];
        int index = 0;
        while(!stack.isEmpty()) {
            answer[index++] = stack.pop();
        }
        
        return answer;
    }
}