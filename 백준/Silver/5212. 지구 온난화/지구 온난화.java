import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<int[]> sinkList = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        
        List<int[]> landList = new ArrayList<>();
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'X') {
                    landList.add(new int[] {i, j});
                }
            }
        }
        br.close();
        
        for(int[] land : landList) {
            sink(land[0], land[1]);
        }
        
        // 가라앉은 섬을 바다로 수정
        for(int[] sinkLand : sinkList) {
            int x = sinkLand[0];
            int y = sinkLand[1];
            map[x][y] = '.';
        }
        
        // 최소 지도 크기 설정
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == 'X') {
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = minX; i <= maxX; i++) {
            for(int j = minY; j <= maxY; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    // 물에 가라앉을 섬 판단
    private static void sink(int x, int y) {
        int seaCnt = 0;
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 인접한 칸이 범위 밖이거나 바다인 경우
            // 주변 바다 수 증가
            if((nx < 0 || nx >= R || ny < 0 || ny >= C) || map[nx][ny] == '.') {
                seaCnt += 1;
            }
        }
        
        // 인접한 바다의 수가 3개 이상인 경우
        // 가라앉을 섬 목록에 해당 섬 위치 추가
        if(seaCnt >= 3) {
            sinkList.add(new int[] {x, y});
        }
    }
}