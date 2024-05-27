class Solution {
    static int[][] dp;
    
    public int solution(int[][] land) {
        int n = land.length;
        dp = new int[n][4];
        for(int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }
        
        int answer = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    if(j == k) {
                        continue;
                    }
                    
                    dp[i][j] = Math.max(dp[i][j], land[i][j] + dp[i - 1][k]);
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }
        
        return answer;  
    }
}