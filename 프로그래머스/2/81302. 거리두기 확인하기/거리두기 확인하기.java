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
    
    public static char[][] wr;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {        
        int[] answer = new int[5];
        for(int i = 0; i < 5; i++) {
            wr = new char[5][5];
            
            for(int j = 0; j < 5; j++) {
                wr[j] = places[i][j].toCharArray();
            }
            
            Queue<Point> personQueue = new LinkedList<>();
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    if(wr[j][k] == 'P') {
                        personQueue.offer(new Point(j, k, 0));
                    }
                }
            }
            
            if(personQueue.size() <= 1) {
                answer[i] = 1;
                continue;
            }
            
            answer[i] = bfs(personQueue);
        }
        
        return answer;
    }
    
    public static int bfs(Queue<Point> personQueue) {
        Queue<Point> queue = new LinkedList();
        
        while(!personQueue.isEmpty()) {
            Point point = personQueue.poll();
            queue.offer(point);
            boolean[][] visited = new boolean[5][5];
            visited[point.x][point.y] = true;
            
            while(!queue.isEmpty()) {
                Point now = queue.poll();
                int x = now.x;
                int y = now.y;
                int distance = now.distance;
                if(distance == 2) {
                    queue.clear();
                    break;
                }
                
                for(int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    
                    if(nx < 0 || nx > 4 || ny < 0 || ny > 4) {
                        continue;
                    }
                    
                    if(!visited[nx][ny]) {
                        if(wr[nx][ny] == 'P') {
                            if(distance < 1 || (distance <= 1 && wr[x][y] == 'O')) {
                                return 0;
                            }
                        }
                        
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, distance + 1));
                    }
                }
            }
        }
        
        return 1;
    }
}