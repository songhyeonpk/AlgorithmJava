import java.util.*;

class Solution {
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static char[][] map;
    static Queue<Point> removes = new LinkedList<>();
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 1, 0};
    
    public int solution(int m, int n, String[] board) {
        map = new char[m][n];
        
        for(int i = 0; i < m; i++) {
            String str = board[i];
            for(int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        
        int answer = 0;
        while(true) {
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(map[i][j] != '.') {
                        // 4개의 블록 중 왼쪽 상단의 블록을 기준으로 탐색
                        search(m, n, i, j, map[i][j]);
                    }
                }
            }
            
            // 제거할 블록이 없는 경우 종료
            if(removes.isEmpty()) {
                break;
            }
            
            // 블록 제거
            int cnt = 0;
            while(!removes.isEmpty()) {
                Point p = removes.poll();
                if(map[p.x][p.y] != '#') {
                    map[p.x][p.y] = '#';
                    cnt += 1;
                }
                
                cnt += remove(p.x, p.y);
            }
            answer += cnt;
            
            // 블록 떨어뜨리기
            drop(m, n);
        }
        
        return answer;
    }
    
    // 블록 탐색
    private static void search(int m, int n, int x, int y, char ch) {
        int cnt = 0;
        for(int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(!rangeCheck(m, n, nx, ny)) {
                continue;
            }
            
            if(map[nx][ny] == ch) {
                cnt++;
            }
            
            if(cnt == 3) {
                removes.add(new Point(x, y));
            }
        }
        
    }
    
    private static boolean rangeCheck(int m, int n, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    
    // 블록 삭제
    private static int remove(int x, int y) {
        // 4개의 블록 중 왼쪽 상단 블록을 기준으로 나머지 블록 삭제
        int cnt = 0;
        
        for(int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(map[nx][ny] != '#') {
                map[nx][ny] = '#';
                cnt += 1;
            }
        }
        
        return cnt;
    }
    
    // 블록 떨어뜨리기
    private static void drop(int m, int n) {
        Queue<Character> queue = new LinkedList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = m - 1; j >= 0; j--) {
                if(map[j][i] >= 'A' && map[j][i] <= 'Z') {
                    queue.offer(map[j][i]);
                }
            }
            
            for(int j = m - 1; j >= 0; j--) {
                char ch = '.';
                if(!queue.isEmpty()) {
                    ch = queue.poll();
                }
                
                map[j][i] = ch;
            }
        }
    }
}