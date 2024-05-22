import java.util.*;

class Solution {
    
    public int solution(int n, int k) {
        String newNum = Integer.toString(n, k);
        String[] nums = newNum.split("0");
        
        int answer = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i].equals("")) {
                continue;
            }
            
            long num = Long.parseLong(nums[i]);
            if(prime(num)) {
                answer += 1;
            }
        }
        
        return answer;
    }
    
    private static boolean prime(long n) {
        if(n == 1) {
            return false;
        }
        
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}