import java.util.*;

class Solution {
    static class Node {
        int x, y, cnt;
        
        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    public static boolean[][] visited;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];
        
        int answer = bfs(maps, 0, 0, maps.length - 1, maps[0].length - 1);
        return answer;
    }
    
    public static int bfs(int[][] maps, int startX, int startY, int endX, int endY) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY, 1));
        visited[startX][startY] = true;
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            int x = now.x;
            int y = now.y;
            int cnt = now.cnt;
            
            if(x == endX && y == endY) {
                return cnt;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx > endX || ny < 0 || ny > endY) {
                    continue;
                }
                
                if(maps[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Node(nx, ny, cnt + 1));
                }
            }
        }
        
        return -1;
    }
}