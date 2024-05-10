import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x; 
        int y;
        int cnt;
        
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    static class Chess {
        int idx;
        int cnt;
        
        public Chess(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
    
    static int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};
    static int[][] map;
    static List<Chess> result = new ArrayList<>();
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        
        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        Point knight = new Point(X, Y, 0);
        
        int idx = 1;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            map[A][B] = idx++;
        }
        br.close();
        
        bfs(knight, N);
        
        Collections.sort(result, new Comparator<Chess>(){
            @Override
            public int compare(Chess c1, Chess c2) {
                return c1.idx - c2.idx;
            }
        });
        
        String answer = "";
        for(Chess chess : result) {
            answer += chess.cnt + " ";
        }
        
        System.out.println(answer);
    }
    
    private static void bfs(Point knight, int N) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(knight);
        visited[knight.x][knight.y] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 8; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx <= 0 || nx > N || ny <= 0 || ny > N) {
                    continue;
                }
                
                if(!visited[nx][ny]) {
                    if(map[nx][ny] != 0) {
                        result.add(new Chess(map[nx][ny], now.cnt + 1));
                    }
                    
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny, now.cnt + 1));
                }
            }
        }
    }
}