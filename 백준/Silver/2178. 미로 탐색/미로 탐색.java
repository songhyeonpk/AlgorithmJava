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
    
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(Character.toString(str.charAt(j)));
            }
        }
        br.close();
        
        bfs(0, 0);
        System.out.println(map[N - 1][M - 1]);
    }
    
    static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            
            for(int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                
                if(!visited[nx][ny] && map[nx][ny] == 1) {
                    queue.offer(new Point(nx, ny));
                    visited[nx][ny] = true;
                    map[nx][ny] = map[nowX][nowY] + 1;
                }
            }
        }
    }
}