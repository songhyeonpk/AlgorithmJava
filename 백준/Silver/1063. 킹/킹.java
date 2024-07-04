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
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String k = st.nextToken();
        String s = st.nextToken();
        int N = Integer.parseInt(st.nextToken());
        
        Point king = new Point(k.charAt(1), k.charAt(0));
        Point stone = new Point(s.charAt(1), s.charAt(0));
        
        // 이동 방향
        // 배열 0번 인덱스: x 값
        // 배열 1번 인덱스: y 값
        Map<String, int[]> moveMap = new HashMap<>();
        moveMap.put("R", new int[] {0, 1});
        moveMap.put("L", new int[] {0, -1});
        moveMap.put("B", new int[] {-1, 0});
        moveMap.put("T", new int[] {1, 0});
        moveMap.put("RB", new int[] {-1, 1});
        moveMap.put("LB", new int[] {-1, -1});
        moveMap.put("RT", new int[] {1, 1});
        moveMap.put("LT", new int[] {1, -1});
        
        while(N-- > 0) {
            String move = br.readLine();
            int[] d = moveMap.get(move);
            
            // 킹의 이동 위치
            int knx = king.x + d[0];
            int kny = king.y + d[1];
            
            // 킹의 이동 위치가 범위 밖인 경우
            if(!rangeCheck(knx, kny)) {
                continue;
            }
            
            // 킹이 이동한 위치에 돌이 있을 경우
            // 돌을 킹의 이동 방향으로 한칸 이동
            if(knx == stone.x && kny == stone.y) {
                
                // 돌의 이동 위치가 범위 밖인 경우
                if(!rangeCheck(stone.x + d[0], stone.y + d[1])) {
                    continue;
                }
                
                stone.x += d[0];
                stone.y += d[1];
            }
            
            king.x = knx;
            king.y = kny;
        }
        br.close();
        
        StringBuilder sb = new StringBuilder();
        sb.append((char) king.y).append((char) king.x).append("\n");
        sb.append((char) stone.y).append((char) stone.x);
        
        System.out.println(sb.toString());
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x > '0' && x <= '8' && y >= 'A' && y <= 'H';
    }
}