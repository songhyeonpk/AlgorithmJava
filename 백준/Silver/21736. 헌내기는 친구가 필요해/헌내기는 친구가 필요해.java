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
    static int n;
    static int m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][] visited;
    static int cnt = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];
        
        Point start = null;
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < m; j++) {
                char ch = str.charAt(j);
                if(ch == 'I') {
                    start = new Point(i, j);
                }
                
                map[i][j] = ch;
            }
        }
        br.close();
        
        bfs(start);
        
        System.out.println(cnt == 0 ? "TT" : cnt);    
    }
    
    private static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(!rangeCheck(nx, ny)) {
                    continue;
                }
                
                if(!visited[nx][ny] && map[nx][ny] != 'X') {
                    if(map[nx][ny] == 'P') {
                        cnt += 1;
                    }
                    
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}