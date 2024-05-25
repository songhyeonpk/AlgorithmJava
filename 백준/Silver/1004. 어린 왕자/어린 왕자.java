import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int count = 0;
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            
            int M = Integer.parseInt(br.readLine());
            for(int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                
                boolean startFlag = false;
                boolean endFlag = false;
                
                if(Math.pow(startX - x, 2) + Math.pow(startY - y, 2) > Math.pow(r, 2)) {
                    startFlag = true;
                }
                if (Math.pow(endX - x, 2) + Math.pow(endY - y, 2) > Math.pow(r, 2)) {
                    endFlag = true;
                }
                
                if(startFlag != endFlag) {
                    count++;
                }
            }
            sb.append(count).append("\n");
            count = 0;
        }
        
        br.close();
        System.out.println(sb);
    }
}