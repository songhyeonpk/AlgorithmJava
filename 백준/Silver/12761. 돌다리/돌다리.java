import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int n, cnt;
        
        Point(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        
        br.close();
        
        int result = bfs(A, B, N, M);
        System.out.println(result);
    }
    
    private static int bfs(int A, int B, int N, int M) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N, 0));
        visited[N] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            int n = now.n;
            if(n == M) {
                return now.cnt;
            }
            
            if(rangeCheck(n - 1) && !visited[n - 1]) {
                visited[n - 1] = true;
                queue.add(new Point(n - 1, now.cnt + 1));
            }
            
            if(rangeCheck(n + 1) && !visited[n + 1]) {
                visited[n + 1] = true;
                queue.add(new Point(n + 1, now.cnt + 1));
            }
            
            if(rangeCheck(n + A) && !visited[n + A]) {
                visited[n + A] = true;
                queue.add(new Point(n + A, now.cnt + 1));
            }
            
            if(rangeCheck(n + B) && !visited[n + B]) {
                visited[n + B] = true;
                queue.add(new Point(n + B, now.cnt + 1));
            }
            
            if(rangeCheck(n - A) && !visited[n - A]) {
                visited[n - A] = true;
                queue.add(new Point(n - A, now.cnt + 1));
            }
            
            if(rangeCheck(n - B) && !visited[n - B]) {
                visited[n - B] = true;
                queue.add(new Point(n - B, now.cnt + 1));
            }
            
            if(rangeCheck(n * A) && !visited[n * A]) {
                visited[n * A] = true;
                queue.add(new Point(n * A, now.cnt + 1));
            }
            
            if(rangeCheck(n * B) && !visited[n * B]) {
                visited[n * B] = true;
                queue.add(new Point(n * B, now.cnt + 1));
            }
        }
        
        return -1;
    }
    
    private static boolean rangeCheck(int n) {
        return n >= 0 && n < 100001;
    }
}