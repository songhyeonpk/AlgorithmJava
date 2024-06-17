import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;
        char[][] map;
        
        Point(int x, int y, char[][] map) {
            this.x = x;
            this.y = y;
            this.map = map;
        }
    }
    
    static int N = 8;
    static boolean[][] visited = new boolean[N][N];
    
    // 좌, 좌상, 상, 우상, 우, 우하, 하, 좌하
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[][] map = new char[N][N];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }
        br.close();
        
        Point start = new Point(N - 1, N - N, map);
        int answer = bfs(start, N - N, N - 1);
        
        System.out.println(answer);
    }
    
    private static int bfs(Point start, int ex, int ey) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            int x = now.x;
            int y = now.y;
            char[][] nowMap = now.map;
            
            if(nowMap[x][y] == '#') {
                continue;
            }
            
            if(x == ex && y == ey) {
                return 1;
            }
            
            // 다음 맵
            char[][] nextMap = drop(nowMap);
            
            for(int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                
                if(!visited[nx][ny] && nowMap[nx][ny] == '.') {
                    queue.add(new Point(nx, ny, nextMap));
                }
            }
            
            // 제자리인 경우
            queue.add(new Point(x, y, nextMap));
            
            visited[x][y] = true;
        }
        
        return 0;
    }
    
    private static char[][] drop(char[][] map) {
        char[][] next = new char[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(next[i], '.');
        }
        
        for(int i = N - 2; i >= 0; i--) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == '#') {
                    next[i + 1][j] = map[i][j];
                }
            }
        }
        
        return next;
    }
}