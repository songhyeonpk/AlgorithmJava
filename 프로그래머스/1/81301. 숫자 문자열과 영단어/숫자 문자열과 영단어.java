import java.util.*;

class Solution {
    public int solution(String s) {
        Map<String, Integer> number = new HashMap<>();
        number.put("zero", 0);
        number.put("one", 1);
        number.put("two", 2);
        number.put("three", 3);
        number.put("four", 4);
        number.put("five", 5);
        number.put("six", 6);
        number.put("seven", 7);
        number.put("eight", 8);
        number.put("nine", 9);
        
        String answer = "";
        String numberStr = "";
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch >= '0' && ch <= '9') {
                answer += ch;
                continue;
            }
            
            numberStr += ch;
            if(number.containsKey(numberStr)) {
                answer += number.get(numberStr) + "";
                numberStr = "";
                continue;
            }
        }
        
        return Integer.parseInt(answer);
    }
}