import java.util.*;

class Solution {
    static class Point {
        int x, y, distance;
        
        Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    
    public static int[][] map = new int[51 * 2][51 * 2];
    public static boolean[][] visited = new boolean[51 * 2][51 * 2];
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) { 
        for(int i = 0; i < rectangle.length; i++) {
            int lbx = rectangle[i][0];
            int lby = rectangle[i][1];
            int rtx = rectangle[i][2];
            int rty = rectangle[i][3];
            
            /* 
                O O X
                O O X
                위의 예시처럼 한칸 차이의 간격이 존재할 수 있으므로 범위를 *2 수행
            */
            for(int j = lby * 2; j <= rty * 2; j++) {
                for(int k = lbx * 2; k <= rtx * 2; k++) {
                    int value = -1;
                    
                    if(j == lby * 2 || j == rty * 2 || k == lbx * 2 || k == rtx * 2) {
                        if(map[j][k] != -1) {
                            value = 1;
                        }
                    }
                    
                    map[j][k] = value;
                }
            }
        }
        
        Point start = new Point(characterY * 2, characterX * 2, 0);
        Point end = new Point(itemY * 2, itemX * 2, 0);
        int answer = bfs(start, end);
        
        return answer;
    }
    
    public static int bfs(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            int x = now.x;
            int y = now.y;
            int distance = now.distance;
            
            if(x == end.x && y == end.y) {
                return distance / 2;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx > 51 * 2 || ny < 0 || ny > 51 * 2) {
                    continue;
                }
                
                if(!visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, distance + 1));
                }
            }
        }
        
        return 0;
    }
}