import java.util.*;

class Solution {
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int areaCnt;
    static int maxCnt;
    
    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        areaCnt = 0;
        maxCnt = Integer.MIN_VALUE;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && picture[i][j] != 0) {
                    bfs(i, j, m, n, picture);
                }
            }
        }
        
        int[] answer = {areaCnt, maxCnt};
        return answer;
    }
    
    private static void bfs(int x, int y, int m, int n, int[][] picture) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        
        int element = picture[x][y];
        int elementCnt = 1;
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                
                if(!visited[nx][ny] && picture[nx][ny] == element) {
                    visited[nx][ny] = true;
                    elementCnt += 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        
        areaCnt += 1;
        maxCnt = Math.max(maxCnt, elementCnt);
    }
}