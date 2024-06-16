import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        br.close();
        
        for(int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            pq.add(num);
        }
        
        while(M-- > 0) {
            long x = pq.poll();
            long y = pq.poll();
            
            for(int i = 0; i < 2; i++) {
                pq.add(x + y);
            }
        }
        
        long sum = 0;
        while(!pq.isEmpty()) {
            sum += pq.poll();
        }
        
        System.out.println(sum);
    }
}