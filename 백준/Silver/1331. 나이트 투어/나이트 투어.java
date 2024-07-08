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
    
    static boolean[][] visited = new boolean[6][6];
    static int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};
    static List<Point> knightPath = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 나이트 처음 위치
        String start = br.readLine();
        Point startPoint = new Point('6' - start.charAt(1), start.charAt(0) - 'A');
        visited[startPoint.x][startPoint.y] = true;
        knightPath.add(startPoint);
        
        int visitedCnt = 1;
        for(int i = 0; i < 35; i++) {
            String str = br.readLine();
            Point prev = knightPath.get(knightPath.size() - 1);
            Point now = new Point('6' - str.charAt(1), str.charAt(0) - 'A');
            
            // 이미 방문한 위치이거나 마지막 위치에서 현재 위치로 이동할 수 없는 경로라면
            if(visited[now.x][now.y] || !move(prev, now)) {
                break;
            }
            
            visitedCnt += 1;
            visited[now.x][now.y] = true;
            knightPath.add(now);
        }
        br.close();
        
        String answer = "Valid";
        // 모든 체스판을 방문하지 않았거나 마지막 위치에서 시작점으로 돌아오지 못할 경우
        if(visitedCnt < 36 || !move(knightPath.get(knightPath.size() - 1), startPoint)) {
            answer = "Invalid";
        }
        
        System.out.println(answer);
    }
    
    private static boolean move(Point prev, Point now) {
        for(int i = 0; i < 8; i++) {
            int nx = prev.x + dx[i];
            int ny = prev.y + dy[i];
            
            if(nx < 0 || nx >= 6 || ny < 0 || ny >= 6) {
                continue;
            }
            
            if(nx == now.x && ny == now.y) {
                return true;
            }
        }
        
        return false;
    }
}