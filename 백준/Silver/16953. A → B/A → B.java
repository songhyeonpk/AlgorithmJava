import java.io.*;
import java.util.*;

public class Main {
    static long M;
    static long min = Long.MAX_VALUE;
    static long pow = (long)Math.pow(10, 9);
        
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        long N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        
        br.close();
        
        dfs(N, 0);
        System.out.println(min != Long.MAX_VALUE ? min + 1 : -1);
    }
    
    static void dfs(long n, int cnt) {
        if(n == M) {
            min = Math.min(min, cnt);
            return;
        }
        
        if(n > M) {
            return;
        }
        
        dfs(n * 2, cnt + 1);
        dfs(((n % pow) * 10) + 1, cnt + 1);
    }
}