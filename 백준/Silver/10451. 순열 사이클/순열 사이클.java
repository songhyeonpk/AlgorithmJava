import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N + 1];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i < N + 1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            visited = new boolean[N + 1];
            int answer = 0;
            for(int i = 1; i < N + 1; i++) {
                if(!visited[i]) {
                    cycle(i, arr);
                    answer += 1;
                }
            }
            
            sb.append(answer).append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
    
    private static void cycle(int idx, int[] arr) {
        if(visited[idx]) {
            return;
        }
        
        visited[idx] = true;
        cycle(arr[idx], arr);
    }
}