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
    static int k;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result = 0;
    static Point end;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        br.close();
        
        Point start = new Point(n - 1, 0);
        end = new Point(0, m - 1);
        backtracking(start, 1);
        
        System.out.println(result);
    }
    
    private static void backtracking(Point now, int depth) {
        if(depth > k) {
            return;
        }
        
        if((now.x == end.x && now.y == end.y) && depth == k) {
            result += 1;
            return;
        }
        
        visited[now.x][now.y] = true;
        
        for(int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            
            if(!visited[nx][ny] && map[nx][ny] == '.') {
                visited[nx][ny] = true;
                backtracking(new Point(nx, ny), depth + 1);
                visited[nx][ny] = false;
            }
        }
    }
}