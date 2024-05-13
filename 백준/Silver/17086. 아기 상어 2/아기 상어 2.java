import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;
    static class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Point> sharkList = new LinkedList<>();
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        for(int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                if(map[i][j] == 1) {
                    sharkList.offer(new Point(i, j));
                }
            }
        }
        
        br.close();
        
        bfs();
        int safeZoneMax = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                safeZoneMax = Math.max(safeZoneMax, dist[i][j]);
            }
        }
        
        System.out.println(safeZoneMax);
    }
    
    static void bfs() {
        while(!sharkList.isEmpty()) {
            Point shark = sharkList.poll();
            dist[shark.x][shark.y] = 0;
            
            Queue<Point> queue = new LinkedList<>();
            queue.offer(shark);
            
            visited = new boolean[N][M];
            visited[shark.x][shark.y] = true;
            
            while(!queue.isEmpty()) {
                Point now = queue.poll();
                
                for(int i = 0; i < 8; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    
                    if(!range_check(nx, ny) || visited[nx][ny]) {
                        continue;
                    }
                    
                    if(map[nx][ny] == 0) {
                        dist[nx][ny] = Math.min(dist[nx][ny], dist[now.x][now.y] + 1);
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }
    
    static boolean range_check(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}