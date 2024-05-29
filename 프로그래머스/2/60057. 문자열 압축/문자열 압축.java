import java.util.*;

class Solution {
    public int solution(String s) {
        List<Integer> lenList = new ArrayList<>();
        
        int unit = 1;
        
        // 자를 단위가 문자열의 길이보다 같거나 작을때까지 수행
        while(unit <= s.length()) {
            
            // 자른 문자열을 저장
            int cnt = s.length() / unit;
            int len = s.length() % unit == 0 ? cnt : cnt + 1;
            String[] strUnit = new String[len];
            
            int start = 0;
            int idx = 0;
            while(start < s.length()) {
                int end = start + unit;
                if(end > s.length()) {
                    end = s.length();
                }
                
                strUnit[idx++] = s.substring(start, end);
                start = end;
            }
            
            // 문자열 압축 과정
            Stack<String> stack = new Stack<>();
            String result = "";
            for(String str : strUnit) {
                if(!stack.isEmpty() && !stack.peek().equals(str)) {
                    int size = stack.size();
                    if(size > 1) {
                        result += size;
                    }
                    
                    result += stack.pop();
                    stack.clear();
                }
                
                stack.push(str);
            }
            
            if(!stack.isEmpty()) {
                int size = stack.size();
                if(size > 1) {
                    result += size;
                }
                
                result += stack.pop();
            }
            
            // 압축된 문자열의 길이를 저장
            lenList.add(result.length());
            unit += 1;
        }
        
        Collections.sort(lenList);
        
        int answer = lenList.get(0);
        return answer;
    }
}