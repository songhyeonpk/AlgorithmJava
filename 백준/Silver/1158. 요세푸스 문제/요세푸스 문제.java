import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        br.close();
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            queue.offer(i);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        
        int cnt = 1;
        while(true) {
            if(cnt == K) {
                sb.append(queue.poll());
                
                cnt = 1;
                if(queue.isEmpty()) {
                    sb.append(">");
                    break;
                }
                
                sb.append(", ");
                continue;
            } 
            
            queue.add(queue.poll());
            cnt += 1;
        }
        
        System.out.println(sb);
    }
}