class Solution {
    public static int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        
        int num = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                map[i][j] = num++;
            }
        }
        
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
            
            answer[i] = move(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        }
        
        return answer;
    }
    
    public static int move(int minX, int minY, int maxX, int maxY) {
        int[][] copyMap = new int[map.length][map[1].length];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = minX; i <= maxX; i++) {
            for(int j = minY; j <= maxY; j++) {
                if(i == minX || i == maxX || j == minY || j == maxY) {
                    min = Math.min(min, map[i][j]);
                
                    // 왼쪽 상단
                    if(i == minX && j == minY) {
                        map[i][j] = copyMap[i + 1][j];
                        continue;
                    }
                
                    // 오른쪽 하단
                    if(i == maxX && j == maxY) {
                        map[i][j] = copyMap[i - 1][j];
                        continue;
                    }
                
                    // 왼쪽 하단
                    if(i == maxX && j == minY) {
                        map[i][j] = copyMap[i][j + 1];
                        continue;
                    }
                
                    // 오론쪽 상단
                    if(i == minX && j == maxY) {
                        map[i][j] = copyMap[i][j - 1];
                        continue;
                    }
                
                    if(j == maxY) {
                        map[i][j] = copyMap[i - 1][j];
                    }
                
                    if(i == minX) {
                        map[i][j] = copyMap[i][j - 1];
                    }
                
                    if(j == minY) {
                        map[i][j] = copyMap[i + 1][j];
                    }
                
                    if(i == maxX) {
                        map[i][j] = copyMap[i][j + 1];
                    } 
                }
            }
        }
        
        return min;
    }
}