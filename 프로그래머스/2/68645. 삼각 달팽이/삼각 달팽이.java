class Solution {
    static int[][] arr;
    
    public int[] solution(int n) {
        // 아래, 오른쪽, 왼쪽 상단 대각선
        int[] dx = {1, 0, -1};
        int[] dy = {0, 1, -1};
        
        arr = new int[n][n];
        int number = 1;
        int x = 0;
        int y = 0;
        int d = 0;
        while(true) {
            arr[x][y] = number++;
            
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(check(nx, ny, n)) {
                d = (d + 1) % 3;
                nx = x + dx[d];
                ny = y + dy[d];
                
                if(check(nx, ny, n)) {
                    break;
                }
            }
            
            x = nx;
            y = ny;
        }
        
        int[] answer = new int[number - 1];
        int idx = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[i][j] == 0) {
                    continue;
                }
                
                answer[idx++] = arr[i][j];
            }
        }
        
        return answer;
    }
    
    private static boolean check(int x, int y, int n) {
        return x >= n || y >= n || arr[x][y] != 0;
    }
}