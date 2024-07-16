import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int L;
    static int[][] map;
    static boolean[] install;
    static int maxCnt;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        maxCnt = 2 * N;
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
        
        // true : 열 탐색
        // false : 행 탐색
        scan(true);
        scan(false);
        
        System.out.println(maxCnt);
    }
    
    private static void scan(boolean isColumn) {
        for(int i = 0; i < N; i++) {
            
            // 경사로가 겹치는 것을 방지하기 위한 설치 여부 배열
            install = new boolean[N];
            
            for(int j = 1; j < N; j++) {
                // true : 열 탐색
                // false : 행 탐색
                int prevHeight = isColumn ? map[i][j - 1] : map[j - 1][i];
                int currentHeight = isColumn ? map[i][j] : map[j][i];
                
                // 이전 칸과 현재 칸의 높이가 같을 경우
                if(prevHeight == currentHeight) {
                    continue;
                }
                
                // 이전 칸과 현재 칸의 높이 차이가 1이 아닌 경우
                // 경사로를 설치하지 못하고 지나갈 수 없는 길
                int diff = prevHeight - currentHeight;
                if(Math.abs(diff) != 1) {
                    maxCnt -= 1;
                    break;
                }
                
                // 이전 칸이 현재 칸의 높이보다 작을 경우 탐색 범위 초기화
                int start = j - L;
                int end = j - 1;
                
                // 이전 칸이 현재 칸의 높이보다 클 경우 탐색 범위 수정
                if(prevHeight > currentHeight) {
                    start = j;
                    end = j - 1 + L;
                }
                
                // 경사로를 설치할 수 없는 경우 지나갈 수 없는 길
                if(!canInstall(isColumn, i, start, end, Math.min(prevHeight, currentHeight))) {
                    maxCnt -= 1;
                    break;
                }
            }
        }
    }
    
    private static boolean canInstall(boolean isColumn, int fixed, int start, int end, int minHeight) {
        // 탐색 범위가 주어진 범위를 벗어날 경우
        if(start < 0 || end >= N) return false;
        
        for(int i = start; i <= end; i++) {
            int height = isColumn ? map[fixed][i] : map[i][fixed];
            
            // 탐색한 칸의 높이가 다르거나 이미 경사로가 설치된 경우 경사로 설치 불가능
            if(height != minHeight || install[i]) {
                return false;
            }
            
            install[i] = true;
        }
        
        return true;
    }
}