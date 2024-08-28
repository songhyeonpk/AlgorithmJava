import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            
            Set<Integer> set = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
            
            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int result = 0;
                
                // set에 숫자가 존재한다면 결과를 1로 초기화
                if(set.contains(Integer.parseInt(st.nextToken()))) {
                    result = 1;
                }
                
                sb.append(result);
                sb.append("\n");
            }
        }
        br.close();
        
        System.out.println(sb.toString());
    }
}