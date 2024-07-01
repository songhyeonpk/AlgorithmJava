import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char[][] map;
    static int maxSize = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        br.close();
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                square(i, j, map[i][j]);
            }
        }
        
        if(maxSize == Integer.MIN_VALUE) {
            maxSize = 1;
        }
        
        System.out.println(maxSize);
    }
    
    private static void square(int row, int col, char ch) {
        // 가로 탐색
        for(int i = col + 1; i < M; i++) {
            
            // 현재 꼭짓점 값이 시작 꼭짓점 값과 같은 경우
            // 세로, 대각선 탐색
            if(map[row][i] == ch) {
                
                // 다음 꼭짓점까지 거리
                int next = i - col;
                
                // 세로, 대각선 범위를 벗어난 경우 다음 탐색으로 넘어감
                if(!rangeCheck(row + next, col) || !rangeCheck(row + next, col + next)) {
                    continue;
                }
                
                // 세로, 대각선 꼭짓점의 값이 시작 꼭짓점의 값과 동일한 경우 정사각형 크기 구하기
                if(map[row + next][col] == ch && map[row + next][col + next] == ch) {
                    int len = next + 1;
                    maxSize = Math.max(maxSize, len * len);
                }
            }
        }
         
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}