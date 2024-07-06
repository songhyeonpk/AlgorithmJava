import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        if(N == 0) {
            System.out.println(0);
            return;
        }
        
        st = new StringTokenizer(br.readLine());
        br.close();
        
        int cnt = 0;
        Stack<Integer> box = new Stack<>();
        for(int i = 0; i < N; i++) {
            int weight = Integer.parseInt(st.nextToken());
            
            if(box.isEmpty()) {
                box.push(weight);
                continue;
            }
            
            int top = box.pop();
            
            // 박스에 담겨 있는 물건들의 무게와 새로운 물건의 무게의 합이 최대 무게보다 크거나 같을 경우
            if(top + weight >= M) {
                if(top + weight > M) {
                    box.push(weight);
                }
                
                cnt += 1;
            }
            
            // 최대 무게보다 작을 경우
            // 박스에 담겨 있는 물건의 총 무게 업데이트
            if(top + weight < M) {
                box.push(top + weight);
            }
        }
        
        // 박스에 물건이 남아있으면 박스 개수 +1
        if(!box.isEmpty()) {
            cnt += 1;
        }
        
        System.out.println(cnt);
    }
}