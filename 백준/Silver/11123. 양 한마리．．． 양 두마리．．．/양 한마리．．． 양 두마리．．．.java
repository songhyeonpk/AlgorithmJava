import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            visited = new boolean[H][W];
            
            for(int j = 0; j < H; j++) {
                String str = br.readLine();
                
                for(int k = 0; k < W; k++) {
                    map[j][k] = str.charAt(k);
                }
            }
            
            int result = 0;
            for(int j = 0; j < H; j++) {
                for(int k = 0; k < W; k++) {
                    if(!visited[j][k] && map[j][k] == '#') {
                        bfs(j, k, H, W);
                        result += 1;
                    }
                }
            }
            
            sb.append(result).append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
    
    private static void bfs(int x, int y, int h, int w) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    continue;
                }
                
                if(!visited[nx][ny] && map[nx][ny] == '#') {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }
}