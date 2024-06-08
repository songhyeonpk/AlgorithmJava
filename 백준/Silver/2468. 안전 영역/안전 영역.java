import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
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
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        int maxRainfall = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                maxRainfall = Math.max(maxRainfall, map[i][j]);
            } 
        }
        
        int safeZone = Integer.MIN_VALUE;
        for(int k = 0; k < maxRainfall; k++) {
            visited = new boolean[N][N];
            
            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j] && map[i][j] > k) {
                        dfs(i, j, k);
                        cnt++;
                    }
                }
                
                safeZone = Math.max(safeZone, cnt);
            }
        }
        
        br.close();
        System.out.println(safeZone);
    }
    
    static void dfs(int x, int y, int n) {
        visited[x][y] = true;
        
        for(int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            
            if(range_check(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] > n) {
                dfs(nextX, nextY, n);
            }
        }
    }
    
    static boolean range_check(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}