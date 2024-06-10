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
    
    static char[][] map = new char[12][6];
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Point> bursts = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0; i < 12; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < 6; j++) {
                char ch = str.charAt(j);
                map[i][j] = ch;
            }
        }
        
        int count = 0;
        while(true) {
            visited = new boolean[12][6];
            
            // 터질 뿌요 검사
            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {
                    if(!visited[i][j] && map[i][j] != '.') {
                        bfs(i, j, map[i][j]);
                    }
                }
            }
            
            // 더이상 터질 뿌요가 존재하지 않는다면 종료
            if(bursts.isEmpty()) {
                break;
            }
            
            // 뿌요 터뜨리기
            while(!bursts.isEmpty()) {
                Point point = bursts.poll();
                map[point.x][point.y] = '.'; 
            }
            
            // 뿌요 떨어뜨리기
            drop();
            
            // 연쇄 수 증가
            count += 1;
        }
        
        System.out.println(count);
    }
    
    private static void bfs(int x, int y, char ch) {
        Point point = new Point(x, y);
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);
        visited[x][y] = true;
        
        List<Point> removeList = new ArrayList<>();
        removeList.add(point);
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0 ; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6) {
                    continue;
                }
                
                if(!visited[nx][ny] && map[nx][ny] == ch) {
                    visited[nx][ny] = true;
                    
                    Point next = new Point(nx, ny);
                    removeList.add(next);
                    queue.add(next);
                }
            }
        }
        
        if(removeList.size() >= 4) {
            bursts.addAll(removeList);
        }
    }
    
    private static void drop() {
        Queue<Character> queue;
        
        for(int i = 0; i < 6; i++) {
            queue = new LinkedList<>();
            
            for(int j = 11; j >= 0; j--) {
                if(map[j][i] != '.') {
                    queue.add(map[j][i]);
                }
            }
            
            for(int j = 11; j >= 0; j--) {
                char ch = '.';
                if(!queue.isEmpty()) {
                    ch = queue.poll();
                }
                
                map[j][i] = ch;
            }
        }
    }
}