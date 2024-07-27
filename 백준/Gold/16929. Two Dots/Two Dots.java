import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean isCycle = false;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        br.close();
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j]) {
                    dfs(0, i, j, i, j, map[i][j]);
                    
                    // 사이클이 존재할 경우
                    if(isCycle) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        }
        
        System.out.println("No");
    }
    
    private static void dfs(int k, int sx, int sy, int x, int y, char ch) {
        visited[x][y] = true;
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            
            if(k >= 3 && sx == nx && sy == ny) {
                isCycle = true;
                return;
            }
            
            if(!visited[nx][ny] && map[nx][ny] == ch) {
                dfs(k + 1, sx, sy, nx, ny, ch);
            }
        }
        
        visited[x][y] = false;
    }
}