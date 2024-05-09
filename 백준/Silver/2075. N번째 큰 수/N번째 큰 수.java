import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }
        });
        
        StringTokenizer st = null;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < N; j++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }
        }
        br.close();
        
        int result = 0;
        while(N-- > 0) {
            result = pq.poll();
        }
        
        System.out.println(result);
    }
}