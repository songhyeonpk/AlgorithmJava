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
    
    static class Coin {
        Point first, second;
        int cnt;
        
        Coin(Point first, Point second, int cnt) {
            this.first = first;
            this.second = second;
            this.cnt = cnt;
        }
    }
    
    static int N;
    static int M;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        
        List<Point> coinList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'o') {
                    coinList.add(new Point(i, j));
                }
            }
        }
        br.close();
        
        Coin coins = new Coin(coinList.get(0), coinList.get(1), 0);
        int answer = bfs(coins);
        
        System.out.println(answer);
    }
    
    private static int bfs(Coin coins) {
        Queue<Coin> queue = new LinkedList<>();
        queue.add(coins);
        
        while(!queue.isEmpty()) {
            Coin now = queue.poll();
            Point first = now.first;
            Point second = now.second;
            if(now.cnt >= 10) {
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                int fnx = first.x + dx[i];
                int fny = first.y + dy[i];
                int snx = second.x + dx[i];
                int sny = second.y + dy[i];
                
                // 각 동전이 떨어졌는지 확인
                boolean firstDrop = drop(fnx, fny);
                boolean secondDrop = drop(snx, sny);
                
                // 모두 떨어진 경우 탐색을 진행하지 않음
                if(firstDrop && secondDrop) {
                    continue;
                }
                
                // 두 동전 중 하나라도 떨어진 경우 현재 이동 횟수 + 1 반환
                if(firstDrop || secondDrop) {
                    return now.cnt + 1;
                }
                
                Point nextFirst = move(first, fnx, fny);
                Point nextSecond = move(second, snx, sny);
                queue.add(new Coin(nextFirst, nextSecond, now.cnt + 1));
            }
        }
        
        return -1;
    }
    
    private static boolean drop(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
    
    private static Point move(Point now, int nx, int ny) {
        Point next = now;
        
        if(map[nx][ny] != '#') {
            next = new Point(nx, ny);
        }
        
        return next;
    }
}