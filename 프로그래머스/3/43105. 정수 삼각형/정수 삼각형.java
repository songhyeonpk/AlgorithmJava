import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        if(triangle.length == 1) return triangle[0][0];
        
        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = triangle[0][0];
        
        int answer = Integer.MIN_VALUE;
        for(int i = 1; i < triangle.length; i++) {
            for(int j = 0; j < triangle[i].length; j++) {
                // 위 칸에 오른쪽의 수만 있는 경우
                if(j - 1 < 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + triangle[i][j]);
                
                // 위 칸에 왼쪽의 수만 있는 경우
                else if(dp[i - 1][j] == -1) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + triangle[i][j]);
                
                // 위 칸에 왼쪽, 오른쪽 모두 있는 경우
                else if(dp[i - 1][j - 1] != -1 && dp[i - 1][j] != -1) dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j - 1] + triangle[i][j], dp[i - 1][j] + triangle[i][j]));
                
                
                answer = Math.max(answer, dp[i][j]);
            }
        }
        
        return answer;
    }
}