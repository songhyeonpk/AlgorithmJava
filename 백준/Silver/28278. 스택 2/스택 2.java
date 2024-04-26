import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            
            if(n == 1) {
                int num = Integer.parseInt(st.nextToken());
                stack.push(num);
                continue;
            }
            
            if(n == 2) {
                int result = -1;
                if(!stack.isEmpty()) {
                    result = stack.pop();
                }
                
                sb.append(result);
            }
            
            if(n == 3) {
                sb.append(stack.size());
            }
            
            if(n == 4) {
                int result = 0;
                if(stack.isEmpty()) {
                    result = 1;
                }
                
                sb.append(result);
            }
            
            if(n == 5) {
                int result = -1;
                if(!stack.isEmpty()) {
                    result = stack.get(stack.size() - 1);
                }
                
                sb.append(result);
            }
            
            sb.append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
}