import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int safeZoneCnt = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0);
        br.close();
        System.out.println(safeZoneCnt);
    }
    
    static void dfs(int wallCnt) {
        if(wallCnt == 3) {
            bfs();
            return;
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wallCnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }
    
    static void bfs() {
        int[][] copyMap = new int[N][M];
        Queue<Point> queue = new LinkedList<>();
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
                
                if(copyMap[i][j] == 2) {
                    queue.offer(new Point(i, j));
                }
            }
        }
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                
                if(range_check(nextX, nextY) && copyMap[nextX][nextY] == 0) {
                    copyMap[nextX][nextY] = 2;
                    queue.offer(new Point(nextX, nextY));
                }
            }
        }
        
        safeZone(copyMap);
    }
    
    static void safeZone(int[][] copyMap) {
        int cnt = 0;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(copyMap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        
        safeZoneCnt = Math.max(safeZoneCnt, cnt);
    }
    
    static boolean range_check(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}