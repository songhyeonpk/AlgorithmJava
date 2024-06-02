import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int index = 0;
        int num = 1;
        while(true) {
            if(stack.get(stack.size() - 1) > N) {
                sb.setLength(0);
                sb.append("NO");
                break;
            } else if (index == arr.length) {
                break;
            }
            
            if(arr[index] == stack.get(stack.size() - 1)) {
                stack.pop();
                sb.append("-").append("\n");
                index++;
                continue;
            }
            
            stack.push(num);
            sb.append("+").append("\n");
            num++;
        }
        
        System.out.println(sb);
    }
}