import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, dist, cnt;
        
        Point(int x, int y, int dist, int cnt) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.cnt = cnt;
        }
    }
    
    static int N;
    static int M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];
        
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        br.close();
        
        System.out.println(bfs(0, 0));
    }
    
    static int bfs(int x, int y) {
        visited[x][y][0] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y, 1, 0));
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            if(now.x == N - 1 && now.y == M - 1) {
                return now.dist;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(!rangeCheck(nx, ny)) {
                    continue;
                }
                
                if(map[nx][ny] == 0 && !visited[nx][ny][now.cnt]) {
                    queue.offer(new Point(nx, ny, now.dist + 1, now.cnt));
                    visited[nx][ny][now.cnt] = true;
                } else if (map[nx][ny] == 1) {
                    if(now.cnt == 1) {
                        continue;
                    }
                    
                    if(!visited[nx][ny][now.cnt + 1]) {
                        queue.offer(new Point(nx, ny, now.dist + 1, now.cnt + 1));
                        visited[nx][ny][now.cnt + 1] = true;
                    }
                }
            }
        }
        
        return -1;
    }
    
    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}