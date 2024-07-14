import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int cnt = 0;
        while(N-- > 0) {
            String word = br.readLine();
            
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                 
                // 스택이 비어있지 않고 스택의 가장 최근에 들어온 원소와 문자가 동일하다면 스택에서 원소 삭제
                if(!stack.isEmpty() && stack.peek() == ch) {
                    stack.pop();
                    continue;
                }
                
                stack.push(ch);
            }
            
            if(stack.isEmpty()) {
                cnt += 1;
            }
        }
        br.close();
        
        System.out.println(cnt);
    }
}