import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sheepCnt = 0;
    static int wolfCnt = 0;
    static int totalSheepCnt = 0;
    static int totalWolfCnt = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < m; j++) {
                char ch = str.charAt(j);
                if(ch == 'o') {
                    totalSheepCnt += 1;
                }
                
                if(ch == 'v') {
                    totalWolfCnt += 1;
                }
                
                map[i][j] = ch;
            }
        }
        br.close();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && map[i][j] != '#') {
                    sheepCnt = 0;
                    wolfCnt = 0;
                    if(map[i][j] == 'o') sheepCnt += 1;
                    if(map[i][j] == 'v') wolfCnt += 1;
                    
                    visited[i][j] = true;
                    dfs(i, j);
                    
                    if(sheepCnt > wolfCnt) {
                        totalWolfCnt -= wolfCnt;
                    } else {
                        totalSheepCnt -= sheepCnt;
                    }
                }
            }
        }
        
        System.out.println(totalSheepCnt + " " + totalWolfCnt);
    }
    
    private static void dfs(int x, int y) {
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            
            if(!visited[nx][ny] && map[nx][ny] != '#') {
                if(map[nx][ny] == 'o') {
                    sheepCnt += 1;
                }
                
                if(map[nx][ny] == 'v') {
                    wolfCnt += 1;
                }
                
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }
}