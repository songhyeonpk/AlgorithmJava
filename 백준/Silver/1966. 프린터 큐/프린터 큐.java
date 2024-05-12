import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            
            LinkedList<int[]> queue = new LinkedList<>();
            int cnt = 0;
            
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                queue.offer(new int[] {j, Integer.parseInt(st.nextToken())});
            }
            
            while(!queue.isEmpty()) {
                int[] arr = queue.poll();
                boolean checkMax = true;
                
                for(int s = 0; s < queue.size(); s++) {
                    if(arr[1] < queue.get(s)[1]) {
                        queue.offer(arr);
                        for(int t = 0; t < s; t++) {
                            queue.offer(queue.poll());
                        }
                        checkMax = false;
                        break;
                    }
                }
                
                if(!checkMax) {
                    continue;
                }
                
                cnt++;
                
                if(arr[0] == index) {
                    break;
                }
            }
            sb.append(cnt).append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
}