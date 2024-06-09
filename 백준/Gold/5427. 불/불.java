import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, time;
        
        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    static int h;
    static int w;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Point> queue;
    static Queue<int[]> fires;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new boolean[h][w];
            queue = new LinkedList<>();
            fires = new LinkedList<>();
            
            for(int i = 0; i < h; i++) {
                String str = br.readLine();
                for(int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);
                    
                    if(map[i][j] == '@') {
                        queue.offer(new Point(i, j, 1));
                    }
                    
                    if(map[i][j] == '*') {
                        fires.offer(new int[] {i, j});
                    }
                }
            }
            
            int result = bfs();
            sb.append(result == -1 ? "IMPOSSIBLE" : result).append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
    
    static int bfs() {
        while(!queue.isEmpty()) {
            int fSize = fires.size();
            for(int i = 0; i < fSize; i++) {
                int[] now = fires.poll();
                int x = now[0];
                int y = now[1];
                
                for(int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    
                    if(nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        continue;
                    }
                    
                    if(map[nx][ny] == '.' || map[nx][ny] == '@') {
                        fires.offer(new int[] {nx, ny});
                        map[nx][ny] = '*';
                    }
                }
            }
            
            List<Point> before = new ArrayList<>();
            int qSize = queue.size();
            for(int i = 0; i < qSize; i++) {
                Point now = queue.poll();
                visited[now.x][now.y] = true;
                
                if(now.x == 0 || now.x == h - 1 || now.y == 0 || now.y == w - 1) {
                    return now.time;
                }
                
                for(int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];
                    
                    if(nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        continue;
                    }
                    
                    if(!visited[nx][ny] && map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, now.time + 1));
                        map[nx][ny] = '@';
                    }
                }
                
                before.add(new Point(now.x, now.y, 0));
            }
            
            for(Point p : before) {
                map[p.x][p.y] = '.';
            }
        }
        
        return -1;
    }
}