import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }
        br.close();
        
        int cnt = 0;
        for(int i = 0; i < R; i++) {
            if(dfs(i, 0)) {
                cnt += 1;
            }
        }
        
        System.out.println(cnt);
    }
    
    private static boolean dfs(int x, int y) {
        if(y == C - 1) {
            return true;
        }
        
        visited[x][y] = true;
        for(int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }
            
            if(!visited[nx][ny] && map[nx][ny] == '.') {
                if(dfs(nx, ny)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}