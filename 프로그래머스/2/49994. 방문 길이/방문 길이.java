import java.util.*;

class Solution {
    public int solution(String dirs) {
        int x = 0;
        int y = 0;
        int answer = 0;
        List<String> visited = new ArrayList<>();
        for(int i = 0; i < dirs.length(); i++) {
            char ch = dirs.charAt(i);
            int nx = x;
            int ny = y;
            
            if(ch == 'U') {
                ny += 1;
            }
            
            if(ch == 'D') {
                ny -= 1;
            }
            
            if(ch == 'L') {
                nx -= 1;
            }
            
            if(ch == 'R') {
                nx += 1;
            }
            
            if(!rangeCheck(nx, ny)) {
                continue;
            }
            
            String path = x + "" + y + "" + nx + "" + ny;
            String reversePath = nx + "" + ny + "" + x + "" + y;
            if(!visited.contains(path) && !visited.contains(reversePath)) {
                answer += 1;
                visited.add(path);
                visited.add(reversePath);
            }
            
            x = nx;
            y = ny;
        }
        
        return answer;
    }
    
    private boolean rangeCheck(int x, int y) {
        return x >= -5 && x <= 5 && y >= -5 && y <= 5;
    }
}