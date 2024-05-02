import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, cnt;
        
        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int n;
    static int[] dx = {-2, -2, 0, 0, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -1, 1};
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        br.close();
        
        int result = bfs(r1, c1, r2, c2);
        System.out.println(result);
    }
    
    private static int bfs(int r1, int c1, int r2, int c2) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r1, c1, 0));
        visited[r1][c1] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if(now.x == r2 && now.y == c2) {
                return now.cnt;
            }
            
            for(int i = 0; i < 6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, now.cnt + 1));
                }
            }
        }
        
        return -1;
    }
}