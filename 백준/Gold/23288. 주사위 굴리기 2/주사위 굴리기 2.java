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
    
    static class Dice {
        // 0: top
        // 1: bottom
        // 2: left
        // 3: right
        // 4: front
        // 5: back
        int[] pages;
        
        Dice(int[] pages) {
            this.pages = pages;
        }
        
        // 굴리기
        void roll(int direction) {
            // 위쪽
            int[] newPages = new int[] {this.pages[4], this.pages[5], this.pages[2], this.pages[3], this.pages[1], this.pages[0]};
            
            // 오른족
            if(direction == 1) {
                newPages = new int[] {this.pages[2], this.pages[3], this.pages[1], this.pages[0], this.pages[4], this.pages[5]};
            }
            
            // 아래쪽
            if(direction == 2) {
                newPages = new int[] {this.pages[5], this.pages[4], this.pages[2], this.pages[3], this.pages[0], this.pages[1]};
            }
            
            // 왼쪽
            if(direction == 3) {
                newPages = new int[] {this.pages[3], this.pages[2], this.pages[0], this.pages[1], this.pages[4], this.pages[5]};
            }
            
            this.pages = newPages;
        }
    }
    
    static class Board {
        Point point;
        Dice dice;
        int direction;
        
        Board(Point point, Dice dice, int direction) {
            this.point = point;
            this.dice = dice;
            this.direction = direction;
        }
    }
    
    static int N;
    static int M;
    static int K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int score = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        
        Point point = new Point(0, 0);
        Dice dice = new Dice(new int[] {1, 6, 4, 3, 5, 2});
        
        move(point, dice);
        
        System.out.println(score);
    }
    
    private static void move(Point p, Dice d) {
        Board current = new Board(p, d, 1);
        
        while(K-- > 0) {
            int direction = current.direction;
            
            // 주사위 이동 위치
            Point now = new Point(current.point.x + dx[direction], current.point.y + dy[direction]);
            
            // 이동할 위치가 범위를 벗어날 경우
            // 기존 위치에서 정반대 방향으로 한칸 이동
            if(!rangeCheck(now.x, now.y)) {
                direction = (direction + 2) % 4;
                now.x = current.point.x + dx[direction];
                now.y = current.point.y + dy[direction];
            }
            
            // 주사위 굴리기
            Dice dice = current.dice;
            dice.roll(direction);
            
            // 이동한 위치와 동서남북 방향으로 근접한 위치 bfs 탐색
            // 주사위가 위치하고 있는 점수와 같은 점수인 위치 개수 계산 
            Queue<Point> queue = new LinkedList<>();
            queue.add(now);
            visited = new boolean[N][M];
            visited[now.x][now.y] = true;
            
            int cnt = 1;
            while(!queue.isEmpty()) {
                Point point = queue.poll();
                
                for(int i = 0; i < 4; i++) {
                    int nx = point.x + dx[i];
                    int ny = point.y + dy[i];
                    
                    if(!rangeCheck(nx, ny)) {
                        continue;
                    }
                    
                    if(!visited[nx][ny] && map[point.x][point.y] == map[nx][ny]) {
                        visited[nx][ny] = true;
                        cnt += 1;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
            
            int boardScore = map[now.x][now.y];
            int diceBottom = dice.pages[1];
            
            // 주사위 아랫면 정수가 현재 위치의 보드판 점수보다 클 경우
            // 시계 방향으로 90도 방향 회전
            if(diceBottom > boardScore) {
                direction = (direction + 1) % 4;
            }
            
            // 주사위 아랫면 정수가 현재 위치의 보드판 점수보다 작을 경우
            // 반시계 방향으로 90도 방향 회전
            if(diceBottom < boardScore) {
                direction = (direction + 3) % 4; 
            }
            
            score += (boardScore * cnt);
            current = new Board(now, dice, direction);
        }
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}