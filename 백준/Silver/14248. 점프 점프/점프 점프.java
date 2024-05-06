import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] visited;
    static int result = 1;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        visited = new boolean[n + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int x = Integer.parseInt(br.readLine());
        br.close();
        
        bfs(x);
        System.out.println(result);
    }
    
    private static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        visited[x] = true;
        
        while(!queue.isEmpty()) {
            int nowX = queue.poll();
            int range = arr[nowX];
            
            if(nowX + range <= n && !visited[nowX + range]) {
                result += 1;
                visited[nowX + range] = true;
                queue.offer(nowX + range);
            }
          
            if(nowX - range > 0 && !visited[nowX - range]) {
                result += 1;
                visited[nowX - range] = true;
                queue.offer(nowX - range);
            }
        }
    }
}