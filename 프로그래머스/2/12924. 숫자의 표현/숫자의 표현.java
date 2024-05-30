class Solution {
    public int solution(int n) {
        int start = 1;
        int end = 1;
        int answer = 0;
        while(start <= end) {
            int sum = 0;
            for(int i = start; i <= end; i++) {
                sum += i;
            }
            
            if(sum < n) {
                end += 1;
            } 
            
            if(sum >= n) {
                if(sum == n) {
                    answer += 1;
                }
                
                start += 1;
            }
        }
        
        return answer;
    }
}