import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Deque<String> deque = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer nums = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            String qos = st.nextToken();
            String num = nums.nextToken();
            if(qos.equals("1")) {
                continue;
            }
            
            deque.addLast(num);
        }
        
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        br.close();
        
        StringBuilder sb = new StringBuilder();
        while(M-- > 0) {
            String num = st.nextToken();
            
            deque.addFirst(num);
            sb.append(deque.pollLast()).append(" ");
        }
        
        System.out.println(sb);
    }
}