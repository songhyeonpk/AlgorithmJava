import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();

            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        br.close();

        int result = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j]) {
                    if(map[i][j] == '-') {
                        dfs(i, j, 0, 2);
                    }

                    if(map[i][j] == '|') {
                        dfs(i, j, 2, 4);
                    }

                    result += 1;
                }
            }
        }

        System.out.println(result);
    }

    private static void dfs(int x, int y, int rs, int re) {
        visited[x][y] = true;

        for(int i = rs; i < re; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }

            if(!visited[nx][ny] && map[nx][ny] == map[x][y]) {
                visited[nx][ny] = true;
                dfs(nx, ny, rs, re);
            }
        }
    }
}