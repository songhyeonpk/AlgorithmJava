import java.io.*;
import java.util.*;

public class Main {
    static int[] nums;
    static int[] op;
    static int maxNum = Integer.MIN_VALUE;
    static int minNum = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        op = new int[4];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        br.close();
        for(int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        
        backtracking(1, nums[0]);
        
        System.out.println(maxNum);
        System.out.println(minNum);
    }
    
    private static void backtracking(int depth, int result) {
        if(depth >= nums.length) {
            maxNum = Math.max(maxNum, result);
            minNum = Math.min(minNum, result);
            return;
        }
        
        for(int i = 0; i < op.length; i++) {
            if(op[i] != 0) {
                op[i] -= 1;
                switch(i) {
                    case 0:
                        backtracking(depth + 1, result + nums[depth]);
                        break;
                    case 1:
                        backtracking(depth + 1, result - nums[depth]);
                        break;
                    case 2:
                        backtracking(depth + 1, result * nums[depth]);
                        break;
                    case 3: 
                        int r = result / nums[depth];
                        if(result < 0) r = -(Math.abs(result) / nums[depth]);
                        backtracking(depth + 1, r);
                        break;
                }
                op[i] += 1;
            }
        }
    }
}