import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int y, x, d;
        
        Point(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
    
    static int C;
    static int R;
    static boolean[][] visited;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];
        
        int K = Integer.parseInt(br.readLine());
        br.close();
        
        if(C * R < K) {
            System.out.println(0);
            return;
        }
        
        String answer = allocation(new Point(0, 0, 0), K - 1);
        System.out.println(answer);
    }
    
    private static String allocation(Point now, int K) {
        visited[now.y][now.x] = true;
        
        while(K-- > 0) {
            int direction = now.d;
            int ny = now.y + dy[direction];
            int nx = now.x + dx[direction];
            
            if(ny < 0 || ny >= R || nx < 0 || nx >= C || visited[ny][nx]) {
                direction = (direction + 1) % 4;
                ny = now.y + dy[direction];
                nx = now.x + dx[direction];
            }
            
            visited[ny][nx] = true;
            now = new Point(ny, nx, direction);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(now.x + 1);
        sb.append(" ");
        sb.append(now.y + 1);
        
        return sb.toString();
    }
}