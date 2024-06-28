import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] coins = new int[n];
        
        // 각 합을 만드는데 필요한 동전의 개수를 저장하기 위한 배열
        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for(int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        
        // 가지고 있는 동전으로 만들 수 있는 합의 필요한 최소 동전 개수 구하기
        for(int i = 0; i < n; i++) {
            int coin = coins[i];
            
            for(int j = coin; j <= k; j++) {
                // 현재 합에서 현재 동전 값을 뺀 합이 0보다 같거나 크고 이미 합을 만들 수 있는 경우라면
                // 현재 위치에 현재 동전 값을 뺀 합의 값 + 1을 저장
                if(j - coin >= 0 && dp[j - coin] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        
        int answer = dp[k];
        
        // 주어진 동전들로 만들 수 없는 합이라면 -1을 저장
        if(answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        
        System.out.println(answer);
    }
}