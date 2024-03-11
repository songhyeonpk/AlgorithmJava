import java.util.*;

class Solution {
    static class Node {
        int x;
        int y;
        int time;
        
        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    public static int n;
    public static int m;
    public static char[][] map;
    public static boolean[][] visited;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];
        visited = new boolean[n][m];
        
        Node startNode = null;
        Node endNode = null;
        Node leverNode = null;
        for(int i = 0; i < n; i++) {
            String str = maps[i];
            for(int j = 0; j < m; j++) {
                char ch = str.charAt(j);
                
                if(ch == 'S') {
                    startNode = new Node(i, j, 0);
                }
                
                if(ch == 'E') {
                    endNode = new Node(i, j, 0);
                }
                
                if(ch == 'L') {
                    leverNode = new Node(i, j, 0);
                }
                
                map[i][j] = ch;
            }
        }
        
        // 시작 위치에서 레버까지의 최소시간을 먼저 구함.
        int answer = bfs(startNode.x, startNode.y, leverNode.x, leverNode.y);
        
        // 시작 위치에서 레버까지 이동할 수 있다면,
        // 이미 지나온 통로를 다시 지날 수 있으므로 visited 배열을 초기화하고 레버 위치에서 도착 위치까지의 최소시간을 구함.
        if(answer > -1) {
            visited = new boolean[n][m];
            int temp = bfs(leverNode.x, leverNode.y, endNode.x, endNode.y);
            
            // 도착 위치까지 갈 수 없으면 -1로 초기화
            answer = (temp == -1) ? -1 : answer + temp;
        }
        
        return answer;
    }
    
    public int bfs(int startX, int startY, int endX, int endY) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY, 0));
        visited[startX][startY] = true;
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            int x = now.x;
            int y = now.y;
            int time = now.time;
            
            if(x == endX && y == endY) {
                return time;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(validateIndex(nx, ny) && !visited[nx][ny] && map[nx][ny] != 'X') {
                    visited[nx][ny] = true;
                    queue.offer(new Node(nx, ny, time + 1));
                }
            }
        }
        
        return -1;
    }
    
    public static boolean validateIndex(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
}