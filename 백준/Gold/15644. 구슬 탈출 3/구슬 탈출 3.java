import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;
        String path;
        
        Point(int x, int y, String path) {
            this.x = x;
            this.y = y;
            this.path = path;
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
    
    static int N;
    static int M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dc = {'U', 'D', 'L', 'R'};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        
        Point red = null;
        Point blue = null;
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
            
            for(int j = 1; j < M - 1; j++) {
                if(map[i][j] == 'R') {
                    red = new Point(i, j, "");
                }
                
                if(map[i][j] == 'B') {
                    blue = new Point(i, j, "");
                }
            }
        }
        br.close();
        
        Bead beads = new Bead(red, blue, 0);
        String answer = bfs(beads);
        
        System.out.println(answer);
    }
    
    private static String bfs(Bead beads) {
        Queue<Bead> queue = new LinkedList<>();
        queue.add(beads);
        visited[beads.red.x][beads.red.y][beads.blue.x][beads.blue.y] = true;
        
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            Bead now = queue.poll();
            Point red = now.red;
            Point blue = now.blue;
            if(now.cnt >= 10) {
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                Point nxRed = move(red, i);
                Point nxBlue = move(blue, i);
                
                if(map[nxBlue.x][nxBlue.y] == 'O') {
                    continue;
                }
                
                if(map[nxRed.x][nxRed.y] == 'O') {
                    sb.append(now.cnt + 1).append("\n");
                    sb.append(nxRed.path);
                    
                    return sb.toString();
                }
                
                // 기울인 후 빨간 구슬과 파란 구슬의 위치가 같은 경우
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
                
                if(!visited[nxRed.x][nxRed.y][nxBlue.x][nxBlue.y]) {
                    visited[nxRed.x][nxRed.y][nxBlue.x][nxBlue.y] = true;
                    queue.add(new Bead(nxRed, nxBlue, now.cnt + 1));
                }
            }
        }
        
        sb.append(-1);
        return sb.toString();
    }
    
    // 구슬 이동 및 경로 추가
    private static Point move(Point point, int dir) {
        int nx = point.x;
        int ny = point.y;
        
        while(map[nx + dx[dir]][ny + dy[dir]] != '#') {
            nx += dx[dir];
            ny += dy[dir];
            
            if(map[nx][ny] == 'O') {
                break;
            }
        }
        
        return new Point(nx, ny, point.path + dc[dir]);
    }
}