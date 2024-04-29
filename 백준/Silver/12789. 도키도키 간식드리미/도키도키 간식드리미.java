import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int seq = 1;
        Queue<Integer> line = new LinkedList<>();
        Stack<Integer> wait = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            line.add(num);
        }
        br.close();
        
        while(!line.isEmpty()) {
            if(seq == line.peek()) {
                line.poll();
                seq += 1;
                continue;
            }
            
            if(!wait.isEmpty() && wait.get(wait.size() - 1) == seq) {
                wait.pop();
                seq += 1;
                continue;
            }
            
            wait.push(line.poll());
        }
        
        String result = "Nice";
        while(!wait.isEmpty()) {
            int num = wait.pop();
            if(num != seq) {
                result = "Sad";
                break;
            }
            
            seq += 1;
        }

        System.out.println(result);
    }
}