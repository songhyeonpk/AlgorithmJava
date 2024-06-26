import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        List<Integer> nums = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(!nums.contains(num)) {
                nums.add(num);
            }
        }
        
        Collections.sort(nums);
        
        StringBuilder sb = new StringBuilder();
        for(int n : nums) {
            sb.append(n).append(" ");
        }
        
        System.out.println(sb);
    }
}