import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[][] visited;
    static int[][] graph;
    static List<Integer> list = new ArrayList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        graph = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(Character.toString(str.charAt(j)));
            }
        }
        br.close();
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && graph[i][j] == 1) {
                    cnt = 1;
                    bfs(i, j);
                    list.add(cnt);
                }
            }
        }
        
        Collections.sort(list);
        
        System.out.println(list.size());
        for(int i : list) {
            System.out.println(i);
        }
    }
    
    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        visited[x][y] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            
            for(int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
                
                if(!rangeCheck(nx, ny)) {
                    continue;
                }
            
                if(!visited[nx][ny] && graph[nx][ny] == 1) {
                    cnt++;
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
        
        return cnt;
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}