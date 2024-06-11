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
    
    static int N;
    static int M;
    static int[][] map;
    static int[][] copyMap;
    static boolean[][] visited;
    static List<Point> virusPoints = new ArrayList<>();
    static Point[] currentVirus;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        currentVirus = new Point[M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 2) {
                    virusPoints.add(new Point(i, j));
                }
                
                map[i][j] = num;
            }
        }
        br.close();
        
        dfs(0, 0);
        
        if(answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        
        System.out.println(answer);
    }
    
    private static void dfs(int depth, int start) {
        if(depth == M) {
            copyMap = new int[N][N];
            for(int i = 0; i < N; i++) {
                Arrays.fill(copyMap[i], -1);
            }
            
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 1) {
                        copyMap[i][j] = -2;
                    }
                }
            }
            
            visited = new boolean[N][N];
            bfs();
            int result = virusCheck();
            if(result != -1) {
                answer = Math.min(answer, result);
            }
            return;
        }
        
        for(int i = start; i < virusPoints.size(); i++) {
            currentVirus[depth] = virusPoints.get(i);
            dfs(depth + 1, i + 1);
        }
    }
    
    private static int virusCheck() {
        int time = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(copyMap[i][j] == -1) {
                    return -1;
                }
                
                time = Math.max(time, copyMap[i][j]);
            }
        }
        
        return time;
    }
    
    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        for(Point point : currentVirus) {
            queue.add(point);
            visited[point.x][point.y] = true;
            copyMap[point.x][point.y] = 0;
        }
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                
                if(!visited[nx][ny] && copyMap[nx][ny] != -2) {
                    visited[nx][ny] = true;
                    copyMap[nx][ny] = copyMap[now.x][now.y] + 1;
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }
}