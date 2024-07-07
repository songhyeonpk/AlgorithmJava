import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < N; j++) {
                char ch = str.charAt(j);
                if(ch != '.') {
                    map[i][j] = -(ch - '0');
                    continue;
                }
                
                map[i][j] = 0;
            }
        }
        br.close();
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] < 0) {
                    mineFind(i, j, -map[i][j]);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] < 0) {
                    sb.append("*");
                    continue;
                }
                
                if(map[i][j] >= 10) {
                    sb.append("M");
                    continue;
                }
                
                sb.append(map[i][j]);
            }
            
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    private static void mineFind(int x, int y, int num) {
        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            
            if(map[nx][ny] >= 0) {
                map[nx][ny] += num;
            }
        }
    }
}