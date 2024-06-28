import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] coins = new int[n];
        int[] dp = new int[k + 1];
        for(int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        
        dp[0] = 1;
        
        // 존재하는 코인으로 만들 수 있는 경우를 하위부터 순차적으로 저장
        for(int i = 0; i < n; i++) {
            int coin = coins[i];
            
            for(int j = coin; j <= k; j++) {
                dp[j] += dp[j - coin];
            }
        }
        
        System.out.println(dp[k]);
    }
}