import java.io.*;
import java.util.*;

public class Main {
    static int[] dp;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        dp = new int[M + 1];
        visited = new boolean[M + 1];
        
        br.close();
        bfs(N, M);
        
        System.out.println(dp[M]);
    }
    
    public static void bfs(int n, int m) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        visited[n] = true;
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            if(now == m) {
                return;
            }
            
            if(now + 1 <= m && !visited[now + 1]) {
                visited[now + 1] = true;
                dp[now + 1] = dp[now] + 1;
                queue.offer(now + 1);
            }
            
            if(now * 2 <= m && !visited[now * 2]) {
                visited[now * 2] = true;
                dp[now * 2] = dp[now] + 1;
                queue.offer(now * 2);
            }
        }
    }
}