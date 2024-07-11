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
    
    static int[][] map = new int[9][9];
    static List<Point> points = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0; i < 9; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(Character.toString(str.charAt(j)));
                if(map[i][j] == 0) {
                    points.add(new Point(i, j));
                }
            }
        }
        br.close();
        
        backtracking(0);
    }
    
    private static void backtracking(int idx) {
        if(idx == points.size()) {
            System.out.println(complete());
            System.exit(0);
        }
        
        // 1부터 9까지 가능한 수 판별
        int x = points.get(idx).x;
        int y = points.get(idx).y;
        for(int i = 1; i <= 9; i++) {
            if(isPossible(x, y, i)) {
                map[x][y] = i;
                backtracking(idx + 1);
                map[x][y] = 0;
            }
        }
    }
    
    private static boolean isPossible(int x, int y, int num) {
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        
        // 안되는 수라면 false return
        for(int i = 0; i < 9; i++) {
            // 세로 판별
            if(map[x][i] == num) return false;
            
            // 가로 판별
            if(map[i][y] == num) return false;
            
            // 3 x 3 판별
            if(map[startX + (i / 3)][startY + (i % 3)] == num) return false;
        }
        
        return true;
    }
    
    private static String complete() {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}