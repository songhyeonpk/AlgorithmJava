class Solution {
    public String solution(int n, int t, int m, int p) {
        int num = 0;
        int cnt = 1;
        String answer = "";
        while(answer.length() < t) {
            String number = Integer.toString(num++, n);
            for(int j = 0; j < number.length(); j++) {
                char ch = number.charAt(j);
                
                if(cnt > m) {
                    cnt = 1;
                }
                
                if(cnt == p) {
                    String result = ch + "";
                    if(!Character.isDigit(ch)) {
                        result = result.toUpperCase();
                    }
                    
                    answer += result;
                }
                
                if(answer.length() == t) {
                    break;
                }
                
                cnt += 1;
            }
        }
        
        return answer;
    }
}