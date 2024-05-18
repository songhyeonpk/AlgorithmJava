import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int size;
    static boolean[][] visited;
    static int[][] graph;
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        for(int i = 0; i < T; i++) {
            size = Integer.parseInt(br.readLine());
            visited = new boolean[size][size];
            graph = new int[size][size];
            
            st = new StringTokenizer(br.readLine(), " ");
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine(), " ");
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            
            bfs(startX, startY);
            sb.append(graph[endX][endY]).append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
    
    private static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            for(int i = 0; i < 8; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
                
                if(!rangeCheck(nx, ny)) {
                    continue;
                }
                
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    graph[nx][ny] = graph[nowX][nowY] + 1;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}