import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + M];
        
        int idx = 0;
        for(int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                arr[idx++] = Integer.parseInt(st.nextToken());
            }    
        }
        br.close();
        
        Arrays.sort(arr);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }
        
        System.out.println(sb);
    }
}