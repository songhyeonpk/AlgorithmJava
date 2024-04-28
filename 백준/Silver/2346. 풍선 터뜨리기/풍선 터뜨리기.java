import java.io.*;
import java.util.*;

public class Main {
    static class Balloon {
        int idx, num;
        
        Balloon(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        Deque<Balloon> deque = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            deque.addLast(new Balloon(i + 1, Integer.parseInt(st.nextToken())));
        }
        
        StringBuilder sb = new StringBuilder();
        Balloon b = deque.pollFirst();
        sb.append(b.idx).append(" ");
        while(true) {
            if(deque.isEmpty()) {
                break;
            }
            
            int num = Math.abs(b.num) - 1;
            if(b.num > 0) {
                while(num-- > 0) {
                    deque.addLast(deque.pollFirst());
                }
                b = deque.pollFirst();
                sb.append(b.idx).append(" ");
                continue;
            }
            
            while(num-- > 0) {
                deque.addFirst(deque.pollLast());
            }
            b = deque.pollLast();
            sb.append(b.idx).append(" ");
        }
        
        System.out.println(sb);
    }
}