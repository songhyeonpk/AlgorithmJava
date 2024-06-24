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
    
    static class Bead {
        Point red, blue;
        int cnt;
        
        Bead(Point red, Point blue, int cnt) {
            this.red = red;
            this.blue = blue;
            this.cnt = cnt;
        }
    }
    
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        
        Point red = null;
        Point blue = null;
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
            
            for(int j = 1; j < M - 1; j++) {
                if(map[i][j] == 'R') {
                    red = new Point(i, j);
                }
                
                if(map[i][j] == 'B') {
                    blue = new Point(i, j);
                }
            }
        }
        br.close();
        
        int answer = bfs(new Bead(red, blue, 0));
        System.out.println(answer);
    }
    
    private static int bfs(Bead beads) {
        Queue<Bead> queue = new LinkedList<>();
        queue.add(beads);
        visited[beads.red.x][beads.red.y][beads.blue.x][beads.blue.y] = true;
        
        while(!queue.isEmpty()) {
            Bead now = queue.poll();
            Point red = now.red;
            Point blue = now.blue;
            
            for(int i = 0; i < 4; i++) {
                Point nxRed = move(red.x, red.y, i);
                Point nxBlue = move(blue.x, blue.y, i);
                
                // 파란 구슬이 들어간 경우 다음 로직을 수행하지 않음
                if(map[nxBlue.x][nxBlue.y] == 'O') {
                    continue;
                }
                
                // 빨간 구슬이 들어간 경우 현재까지의 기울인 횟수 + 1 반환
                if(map[nxRed.x][nxRed.y] == 'O') {
                    return now.cnt + 1;
                }
                
                // 기울인 후 빨간 구슬과 파란 구슬의 위치가 같은 경우
                // 각 구슬이 기울이기 전 위치를 기반으로 위치 재배치
                if(nxRed.x == nxBlue.x && nxRed.y == nxBlue.y) {
                    switch(i) {
                            
                        // 위쪽으로 기울인 경우
                        case 0:
                            nxRed.x += red.x > blue.x ? 1 : 0;
                            nxBlue.x += red.x <= blue.x ? 1 : 0;
                            break;
                         
                        // 아래쪽으로 기울인 경우
                        case 1:
                            nxBlue.x -= red.x > blue.x ? 1 : 0;
                            nxRed.x -= red.x <= blue.x ? 1 : 0;
                            break;
                            
                        // 왼쪽으로 기울인 경우
                        case 2:
                            nxRed.y += red.y > blue.y ? 1 : 0;
                            nxBlue.y += red.y <= blue.y ? 1 : 0;
                            break;
                           
                        // 오른쪽으로 기울인 경우
                        case 3:
                            nxBlue.y -= red.y > blue.y ? 1 : 0;
                            nxRed.y -= red.y <= blue.y ? 1 : 0;
                            break;
                    }
                }
                
                // 각 구슬의 위치를 방문한 적이 없는 경우 방문 처리 및 큐에 추가
                if(!visited[nxRed.x][nxRed.y][nxBlue.x][nxBlue.y]) {
                    visited[nxRed.x][nxRed.y][nxBlue.x][nxBlue.y] = true;
                    queue.add(new Bead(nxRed, nxBlue, now.cnt + 1));
                }
            }
        }
        
        return -1;
    }
    
    private static Point move(int x, int y, int dir) {
        int nx = x;
        int ny = y;
        
        while(map[nx + dx[dir]][ny + dy[dir]] != '#') {
            nx += dx[dir];
            ny += dy[dir];
            
            if(map[nx][ny] == 'O') {
                break;
            }
        }
        
        return new Point(nx, ny);
    }
}