import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            
            switch(n) {
                case 1:
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                    
                case 2:
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                    
                case 3:
                    sb.append(deque.isEmpty() ? -1 : deque.pollFirst());
                    break;
                    
                case 4:
                    sb.append(deque.isEmpty() ? -1 : deque.pollLast());
                    break;
                    
                case 5:
                    sb.append(deque.size());
                    break;
                    
                case 6:
                    sb.append(deque.isEmpty() ? 1 : 0);
                    break;
                    
                case 7:
                    sb.append(deque.isEmpty() ? -1 : deque.peekFirst());
                    break;
                    
                case 8:
                    sb.append(deque.isEmpty() ? -1 : deque.peekLast());
                    break;
            }
            
            if(n != 1 && n != 2) sb.append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
}