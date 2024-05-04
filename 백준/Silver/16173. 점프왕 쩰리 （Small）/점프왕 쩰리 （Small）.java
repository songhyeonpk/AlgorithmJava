import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];
        
        StringTokenizer st = null;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        
        String result = bfs(0, 0);
        System.out.println(result);
    }
    
    private static String bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sx, sy});
        visited[sx][sy] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            if(x == n - 1 && y == n - 1) {
                return "HaruHaru";
            }
            
            int rny = y + map[x][y];
            if(rangeCheck(x, rny) && !visited[x][rny]) {
                visited[x][rny] = true;
                queue.add(new int[] {x, rny});
            }
            
            int dnx = x + map[x][y];
            if(rangeCheck(dnx, y) && !visited[dnx][y]) {
                visited[dnx][y] = true;
                queue.add(new int[] {dnx, y});
            }
        }
        
        return "Hing";
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}