import java.util.*;

class Solution {
    public static int X;
    public static int Y;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int[][] map;
    public static boolean[][] visited;
    public static int sum = 0;
    
    public int[] solution(String[] maps) {
        X = maps.length;
        Y = maps[0].length();
        map = new int[X][Y];
        visited = new boolean[X][Y];
        for(int i = 0; i < X; i++) {
            for(int j = 0; j < Y; j++) {
                char ch = maps[i].charAt(j);
                if(ch == 'X') {
                    map[i][j] = 0;
                    continue;
                }
                
                map[i][j] = Integer.parseInt(Character.toString(ch));
            }
        }
        
        List<Integer> sumList = new ArrayList<>();
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j);
                    sumList.add(sum);
                    sum = 0;
                }
            }
        }
        
        if(sumList.isEmpty()) {
            return new int[] {-1};
        }
        
        Collections.sort(sumList);
        
        int[] result = new int[sumList.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = sumList.get(i);
        }
        
        return result;
    }
    
    public static void dfs(int x, int y) {
        sum += map[x][y];
        visited[x][y] = true;
        
        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            
            if(nx < 0 || ny < 0 || nx >= X || ny >= Y) {
                continue;
            }
            
            if(map[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }
}