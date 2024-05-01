import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, dist;
        
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static int n;
    static int m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int[][] distance;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        distance = new int[n][m];
        visited = new boolean[n][m];
        
        Point start = null;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 2) {
                    start = new Point(i, j, 0);
                }
                
                map[i][j] = num;
                distance[i][j] = -num;
            }
        }
        br.close();
        
        bfs(start);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(distance[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    private static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        distance[start.x][start.y] = 0;
        visited[start.x][start.y] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(!rangeCheck(nx, ny)) {
                    continue;
                }
                
                if(!visited[nx][ny] && map[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    distance[nx][ny] = now.dist + 1;
                    queue.offer(new Point(nx, ny, distance[nx][ny]));
                }
            }
        }
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}