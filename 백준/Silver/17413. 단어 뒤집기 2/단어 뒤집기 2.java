import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        br.close();
        
        String answer = "";
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();
        int idx = 0;
        while(idx < str.length()) {
            char ch = str.charAt(idx);
            
            if(ch == '<') {
                while(!stack.isEmpty()) {
                    answer += stack.pop();
                }
                
                while(str.charAt(idx) != '>') {
                    queue.add(str.charAt(idx++));
                }
                queue.add(str.charAt(idx++));
                
                while(!queue.isEmpty()) {
                    answer += queue.poll();
                }
                
                continue;
            }
            
            if(ch == ' ') {
                while(!stack.isEmpty()) {
                    answer += stack.pop();
                }
                answer += ch;
                idx++;
                continue;
            }
            
            stack.push(ch);
            idx++;
        }
        
        while(!stack.isEmpty()) {
            answer += stack.pop();
        }
        
        System.out.println(answer);
    }
}