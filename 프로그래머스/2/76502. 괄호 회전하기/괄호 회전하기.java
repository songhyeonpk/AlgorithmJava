import java.util.*;

class Solution {
    public int solution(String s) {
        Queue<Character> queue = new LinkedList<>();
        for(int i = 0; i < s.length(); i++) {
            queue.offer(s.charAt(i));
        }
        
        int answer = 0;
        int len = 0;
        while(len++ < s.length()) {
            Stack<Character> stack = new Stack<>();
            
            for(int i = 0; i < queue.size(); i++) {
                char ch = queue.poll();
                
                if(!stack.isEmpty() &&
                   ((ch == ']' && stack.get(stack.size() - 1) == '[') ||                        (ch == ')' && stack.get(stack.size() - 1) == '(') ||                        (ch == '}' && stack.get(stack.size() - 1) == '{'))) {
                    stack.pop();
                    queue.offer(ch);
                    continue;
                }
                
                stack.push(ch);
                queue.offer(ch);
            }
            
            if(stack.isEmpty()) {
                answer++;
            }
            
            queue.offer(queue.poll());
        }
        
        return answer;
    }
}