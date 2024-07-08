import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        // d
        // 0 : 북
        // 1 : 동
        // 2 : 남
        // 3 : 서
        int x, y, direction;
        
        Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
        
        // 회전
        void rotation(char ch) {
            // 반시계 방향으로 90도 회전
            int newDirection = (this.direction + 3) % 4;
            
            // 시계 방향으로 90도 회전
            if(ch == 'R') {
                newDirection = (this.direction + 1) % 4;
            }
            
            this.direction = newDirection;
        }
    }
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<Point> path = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Point now = new Point(0, 0, 2);
        path.add(now);
        
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        br.close();
        
        for(int i = 0; i < N; i++) {
            char ch = str.charAt(i);
            int direction = now.direction;
            if(ch == 'F') {
                now = new Point(now.x + dx[direction], now.y + dy[direction], direction);
                path.add(now);
                continue;
            }
            
            // 'L' or 'R' 일 경우 회전
            now.rotation(ch);
        }
        
        // 제일 작은 x, y 좌표 탐색
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for(Point point : path) {
            minX = Math.min(minX, point.x);
            minY = Math.min(minY, point.y);
        }
        
        // 제일 작은 x, y 좌표가 음수라면 0으로 만들고 차이 값을 모든 x, y 좌표에 더하기
        // 제일 큰 x, y 좌표 탐색
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for(Point point : path) {
            point.x += (-minX);
            point.y += (-minY);
            maxX = Math.max(maxX, point.x);
            maxY = Math.max(maxY, point.y);
        }
        
        char[][] map = new char[maxX + 1][maxY + 1];
        for(int i = 0; i < maxX + 1; i++) {
            Arrays.fill(map[i], '#');
        }
        
        for(Point point : path) {
            map[point.x][point.y] = '.';
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < maxX + 1; i++) {
            for(int j = 0; j < maxY + 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}