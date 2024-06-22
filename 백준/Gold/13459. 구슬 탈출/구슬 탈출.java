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
    
    static int N;
    static int M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException{
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
                    red = new Point(i, j);
                }
                
                if(map[i][j] == 'B') {
                    blue = new Point(i, j);
                }
            }
        }
        br.close();
        
        Bead beads = new Bead(red, blue, 0);
        int answer = bfs(beads);
        
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
            
            // 기울인 횟수가 10번 이상이라면 반복문 탈출
            if(now.cnt >= 10) {
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                // 각 구슬의 다음 위치
                Point nxRed = move(red.x, red.y, i);
                Point nxBlue = move(blue.x, blue.y, i);
                
                // 파란 구슬이 구멍에 빠진 경우
                // 기울이기 과정을 수행하지 않고 queue에 추가하지 않음
                if(map[nxBlue.x][nxBlue.y] == 'O') {
                    continue;
                }
                
                // 빨간 구슬이 구멍에 빠진 경우
                // 성공으로 1 리턴
                if(map[nxRed.x][nxRed.y] == 'O') {
                    return 1;
                }
                
                // 다음 위치가 빨간 구슬과 파란 구슬이 같은 경우
                if(nxRed.x == nxBlue.x && nxRed.y == nxBlue.y) {
                    // 상, 하로 기울인 경우 두 구슬의 현재 위치 x 값을 비교
                    // 좌, 우로 기울인 경우 두 구술의 현재 위치 y 값을 비교
                    switch(i) {
                            
                        // 위쪽으로 기울인 경우
                        // x값이 더 큰 구슬을 한칸 아래로 이동
                        case 0:
                            nxRed.x += red.x > blue.x ? 1 : 0;
                            nxBlue.x += red.x <= blue.x ? 1 : 0;
                            break;
                            
                        // 아래쪽으로 기울인 경우
                        // x값이 더 작은 구슬을 한칸 위로 이동
                        case 1:
                            nxBlue.x -= red.x > blue.x ? 1 : 0;
                            nxRed.x -= red.x <= blue.x ? 1 : 0;
                            break;
                            
                        // 왼쪽으로 기울인 경우
                        // y값이 더 큰 구슬을 한칸 오른쪽으로 이동  
                        case 2:
                            nxRed.y += red.y > blue.y ? 1 : 0;
                            nxBlue.y += red.y <= blue.y ? 1 : 0;
                            break;
                            
                        // 오른쪽으로 기울인 경우
                        // y값이 더 작은 구슬을 한칸 왼쪽으로 이동
                        case 3:
                            nxBlue.y -= red.y > blue.y ? 1 : 0;
                            nxRed.y -= red.y <= blue.y ? 1 : 0;
                            break;
                    }
                }
                
                // 두 구슬의 다음 위치가 방문한 적이 없는 경우면 방문처리 및 queue에 추가
                if(!visited[nxRed.x][nxRed.y][nxBlue.x][nxBlue.y]) {
                    visited[nxRed.x][nxRed.y][nxBlue.x][nxBlue.y] = true;
                    queue.add(new Bead(nxRed, nxBlue, now.cnt + 1));   
                }
            }
        }
        
        return 0;
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