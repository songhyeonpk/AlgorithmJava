import java.util.*;

class Solution {
    
    public String solution(String number, int k) {
        Stack<Character> stack = new Stack<>();
        int len = number.length() - k;
        
        for(int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            
            while(!stack.isEmpty() && k > 0 && stack.get(stack.size() - 1) < ch) {
                stack.pop();
                k -= 1;
            }
            
            stack.push(ch);
        }
        
        String answer = "";
        for(int i = 0; i < len; i++) {
            answer += stack.get(i);
        }
        
        return answer;
    }
}