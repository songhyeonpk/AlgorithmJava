import java.util.*;

class Solution {
    static class Node {
        int x;
        int y;
        int cnt;
        
        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    
    public static int n;
    public static int m;
    public static char[][] map;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        map = new char[n][m];
        
        Node start = null;
        Node end = null;
        for(int i = 0; i < n; i++) {
            String str = board[i];
            for(int j = 0; j < m; j++) {
                char ch = str.charAt(j);
                
                if(ch == 'R') {
                    start = new Node(i, j, 0);
                }
                
                if(ch == 'G') {
                    end = new Node(i, j, 0);
                }
                
                map[i][j] = ch;
            }
        }
        
        return bfs(start, end);
    }
    
    public static int bfs(Node start, Node end) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[n][m];
        visited[start.x][start.y] = true;
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            int x = now.x;
            int y = now.y;
            int cnt = now.cnt;
            System.out.println("x : " + x);
            System.out.println("y : " + y);
            System.out.println("cnt : " + cnt);
            
            if(x == end.x && y == end.y) {
                return cnt;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                while(validateIndex(nx, ny) && map[nx][ny] != 'D') {
                    nx += dx[i];
                    ny += dy[i];
                }
                
                nx -= dx[i];
                ny -= dy[i];
                
                if(visited[nx][ny]) {
                    continue;
                }
                
                visited[nx][ny] = true;
                queue.offer(new Node(nx, ny, cnt + 1));
            }
        }
        
        return -1;
    }
    
    public static boolean validateIndex(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
}