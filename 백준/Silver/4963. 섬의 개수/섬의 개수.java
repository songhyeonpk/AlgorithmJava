import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int w;
    static int h;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            
            if(w == 0 && h == 0) {
                break;
            }
            
            map = new int[h][w];
            visited = new boolean[h][w];
            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int islCnt = 0;
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(!visited[i][j] && map[i][j] == 1) {
                        dfs(i, j);
                        islCnt++;
                    }
                }
            }
            sb.append(islCnt).append("\n");
        }
        
        br.close();
        System.out.println(sb);
    }
    
    static void dfs(int x, int y) {
        visited[x][y] = true;
        
        for(int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            
            if(range_check(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                dfs(nextX, nextY);
            }
        }
    }
    
    static boolean range_check(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }
}