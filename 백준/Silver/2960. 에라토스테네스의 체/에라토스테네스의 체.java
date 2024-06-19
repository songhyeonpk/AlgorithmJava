import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        boolean[] prime = new boolean[N + 1];
        List<Integer> list = new ArrayList<>();
        
        for(int i = 2; i <= N; i++) {
            for(int j = i; j <= N; j += i) {
                if(!prime[j]) {
                    prime[j] = true;
                    list.add(j);
                }
            }
        }
        
        System.out.println(list.get(K - 1));
    }
}