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
    
    static class RobotCleaner {
        Point point;
        int d;
        
        RobotCleaner(Point point, int d) {
            this.point = point;
            this.d = d;
        }
    }
    
    static int[][] map;
    static boolean[][] clean;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        clean = new boolean[N][M];
        
        st = new StringTokenizer(br.readLine());
        Point point = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        RobotCleaner start = new RobotCleaner(point, Integer.parseInt(st.nextToken()));
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        
        int answer = move(start);
        System.out.println(answer);
    }
    
    private static int move(RobotCleaner start) {
        Queue<RobotCleaner> queue = new LinkedList<>();
        queue.add(start);
        
        int cnt = 0;
        while(!queue.isEmpty()) {
            RobotCleaner now = queue.poll();
            Point point = now.point;
            int direction = now.d;
            
            // 현재 칸이 청소되어 있지 않은 빈칸일 경우 청소
            if(!clean[point.x][point.y] && map[point.x][point.y] == 0) {
                clean[point.x][point.y] = true;
                cnt += 1;
            }
            
            // 현재 칸 주변 4칸의 청소되지 않은 빈칸 탐색
            int uncleanCnt = 0;
            for(int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
                
                if(!clean[nx][ny] && map[nx][ny] == 0) {
                    uncleanCnt += 1;
                }
            }
            
            // 현재 칸 주변 4칸에 청소되지 않은 빈칸이 없는 경우
            if(uncleanCnt < 1) {
                
                // 현재 바라보는 방향을 기준으로 한 칸 후진 (0 <-> 2, 1 <-> 3)
                int nx = point.x + dx[(direction + 2) % 4];
                int ny = point.y + dy[(direction + 2) % 4];
                
                // 한 칸 후진한 위치가 벽인 경우 작동 멈춤
                if(map[nx][ny] == 1) {
                    break;
                }
                
                // 후진할 수 있는 경우 후진
                queue.add(new RobotCleaner(new Point(nx, ny), direction));
                continue;
            }
            
            // 현재 칸 주변 4칸에 청소되지 않은 빈칸이 있는 경우
            // 바라보는 방향을 반시계 방향으로 90도 회전
            direction = (direction + 3) % 4;
            
            // 바라보는 방향으로 한 칸 전진
            int nx = point.x + dx[direction];
            int ny = point.y + dy[direction];
            
            // 한 칸 전진한 위치가 아직 청소되지 않은 빈칸일 경우 해당 위치 저장
            if(!clean[nx][ny] && map[nx][ny] == 0) {
                point.x = nx;
                point.y = ny;
            }
            
            queue.add(new RobotCleaner(point, direction));
        }
        
        return cnt;
    }
}