import java.util.*;

class Solution {
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    public static boolean[][] visited;
    public static int maxX;
    public static int maxY;
        
    public int solution(int[][] land) {
        
        visited = new boolean[500][500];
        for(boolean v[] : visited) {
            Arrays.fill(v, false);
        }
        maxX = land.length;
        maxY = land[0].length;
        
        int id = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < land.length; i++) {
            for(int j = 0; j < land[i].length; j++) {
                if(land[i][j] == 1 && visited[i][j] == false) {
                    map.put(id, bfs(land, i, j, id));
                    id++;
                }
            }
        }
        
        int answer = 0;
        boolean[] check = new boolean[map.size() + 1];
        for(int i = 0; i < maxY; i++) {
            int result = 0;
            Arrays.fill(check, false);
            
            for(int j = 0; j < maxX; j++) {
                int key = land[j][i];
                if(key != 0 && !check[key]) {
                    check[key] = true;
                    result += map.get(key);
                }
            }
            
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
    
    private int bfs(int[][] land, int startX, int startY, int id) {
        Queue<int[]> queue = new LinkedList<>(); 
        queue.offer(new int[] {startX, startY});
        land[startX][startY] = id;
        visited[startX][startY] = true;
        
        int count = 1;
        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= maxX || ny >= maxY) {
                    continue;
                }
                
                if(land[nx][ny] == 1 && visited[nx][ny] == false) {
                    queue.offer(new int[] {nx, ny});
                    land[nx][ny] = id;
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}