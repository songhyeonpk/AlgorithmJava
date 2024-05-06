import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n;
    static int[][] map;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        
        StringTokenizer st = null;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        
        Point start = new Point(0, 0);
        Point end = new Point(n - 1, n - 1);
        String result = bfs(start, end);
        System.out.println(result);
    }
    
    private static String bfs(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nextRange = map[nowX][nowY];
            
            if(nowX == end.x && nowY == end.y) {
                return "HaruHaru";
            }
            
            if(rangeCheck(nowX + nextRange, nowY) && !visited[nowX + nextRange][nowY]) {
                visited[nowX + nextRange][nowY] = true;
                queue.offer(new Point(nowX + nextRange, nowY));
            }
            
            if(rangeCheck(nowX, nowY + nextRange) && !visited[nowX][nowY + nextRange]) {
                visited[nowX][nowY + nextRange] = true;
                queue.offer(new Point(nowX, nowY + nextRange));
            }
        }
        
        return "Hing";
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}