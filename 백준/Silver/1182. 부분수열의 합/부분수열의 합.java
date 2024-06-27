import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int S;
    static int[] numbers;
    static int cnt = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        
        st = new StringTokenizer(br.readLine());
        br.close();
        
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i < N; i++) {
            backtracking(numbers[i], i + 1);
        }
        
        System.out.println(cnt);
    }
    
    private static void backtracking(int sum, int start) {
        if(sum == S) {
            cnt += 1;
            
            // return;
            // 뒤에 있는 숫자에 0이 있을 수 있기 때문에 return을 하지 않는다.
        }
        
        for(int i = start; i < N; i++) {
            backtracking(sum + numbers[i], i + 1);
        }
    }
}