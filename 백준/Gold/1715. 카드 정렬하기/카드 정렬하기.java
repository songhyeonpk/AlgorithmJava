import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        
        // 개수가 한개라면 비교할 수 없으므로 0 출력
        if(N == 1) {
            System.out.println(cnt);
            return;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        br.close();
        
        while(true) {
            int sum = pq.poll() + pq.poll();
            cnt += sum;
            if(pq.isEmpty()) {
                break;
            }
            
            pq.add(sum);
        }
        
        System.out.println(cnt);
    }
}