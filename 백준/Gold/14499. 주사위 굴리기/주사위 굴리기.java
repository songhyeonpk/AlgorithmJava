import java.io.*;
import java.util.*;

public class Main {
    static class Dice {
        int top, bottom, left, right, front, back;
        
        Dice(int top, int bottom, int left, int right, int front, int back) {
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
            this.front = front;
            this.back = back;
        }
    }
    
    static int N;
    static int M;
    static int X;
    static int Y;
    static int K;
    static int[][] map;
    static int[] command;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        command = new int[K];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        
        Dice now = new Dice(0, 0, 0, 0, 0, 0);
        simulation(now);
        
        System.out.println(sb);
    }
    
    private static void simulation(Dice d) {
        for(int i = 0; i < K; i++) {
            int dir = command[i];
            int curX = X;
            int curY = Y;
            int curTop = d.top;
            int curBottom = d.bottom;
            int curLeft = d.left;
            int curRight = d.right;
            int curFront = d.front;
            int curBack = d.back;
            
            boolean moved = false;
            // 동
            if(dir == 1 && rangeCheck(curX, curY + 1)) {
                curY += 1;
                curTop = d.left;
                curRight = d.top;
                curBottom = d.right;
                curLeft = d.bottom;
                moved = true;
            }
            
            // 서
            else if(dir == 2 && rangeCheck(curX, curY - 1)) {
                curY -= 1;
                curTop = d.right;
                curLeft = d.top;
                curBottom = d.left;
                curRight = d.bottom;
                moved = true;
            }
            
            // 남
            else if(dir == 4 && rangeCheck(curX + 1, curY)) {
                curX += 1;
                curTop = d.back;
                curFront = d.top;
                curBottom = d.front;
                curBack = d.bottom;
                moved = true;
            }
            
            // 북
            else if(dir == 3 && rangeCheck(curX - 1, curY)){
                curX -= 1;
                curTop = d.front;
                curFront = d.bottom;
                curBottom = d.back;
                curBack = d.top;
                moved = true;
            }
            
            if(!moved) {
                continue;
            }
            
            if(map[curX][curY] == 0) {
                map[curX][curY] = curBottom;
            } else {
                curBottom = map[curX][curY];
                map[curX][curY] = 0;
            }
            
            X = curX;
            Y = curY;
            sb.append(curTop).append("\n");
            d = new Dice(curTop, curBottom, curLeft, curRight, curFront, curBack);
        }
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}