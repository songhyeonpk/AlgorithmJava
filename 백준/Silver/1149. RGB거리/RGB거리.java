import java.io.*;
import java.util.*;

public class Main {
    static int red = 0;
    static int green = 1;
    static int blue = 2;
    static int[][] cost;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        dp = new int[N][3];
        
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][red] = Integer.parseInt(st.nextToken());
            cost[i][green] = Integer.parseInt(st.nextToken());
            cost[i][blue] = Integer.parseInt(st.nextToken());
        }
        br.close();
        
        dp[0][red] = cost[0][red];
        dp[0][green] = cost[0][green];
        dp[0][blue] = cost[0][blue];
        
        System.out.println(Math.min(dp(N - 1, red), Math.min(dp(N - 1, green), dp(N - 1, blue))));
    }
    
    static int dp(int n, int color) {
        if(dp[n][color] == 0) {
            if(color == red) {
                dp[n][color] = Math.min(dp(n - 1, green), dp(n - 1, blue)) + cost[n][red];
            } else if (color == green) {
                dp[n][color] = Math.min(dp(n - 1, red), dp(n - 1, blue)) + cost[n][green];
            } else {
                dp[n][color] = Math.min(dp(n - 1, red), dp(n - 1, green)) + cost[n][blue];
            }
        }
        
        return dp[n][color];
    }
}