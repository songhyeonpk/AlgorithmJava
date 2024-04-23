import java.util.*;

class Solution {
    public String solution(String p) {
        if(distinctionString(p)) {
            return p;
        }
        
        return convert(p);
    }
    
    private static boolean distinctionString(String str) {
        // 문자열 u가 올바른 괄호 문자열인지 판별
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(') {
                stack.push(ch);
                continue;
            }
            
            if(stack.isEmpty() && ch == ')') {
                return false;
            }
            
            stack.pop();
        }
        
        // 문자열 u가 올바른 괄호 문자열인 경우
        if(stack.isEmpty()) {
            return true;
        }
        
        return false;
    }
    
    private static String convert(String w) {
        if(w.equals("")) {
            return "";
        }
        
        String u = "";
        String v = "";
        int openCnt = 0;
        int closeCnt = 0;
        for(int i = 0; i < w.length(); i++) {
            char ch = w.charAt(i);
            if(ch == '(') {
                openCnt++;
            }
                
            if(ch == ')') {
                closeCnt++;
            }
                
            if(openCnt == closeCnt) {
                u = w.substring(0, i + 1);
                v = w.substring(i + 1, w.length());
                break;
            }
        }
        
        // 문자열 u가 올바른 괄호 문자열인 경우
        if(distinctionString(u)) {
            u += convert(v);
            return u;
        }
        
        // 문자열 u가 올바른 괄호 문자열이 아닌 경우
        String newStr = "(";
        newStr += convert(v);
        newStr += ")";
        for(int i = 1; i < u.length() - 1; i++) {
            char ch = u.charAt(i);
            if(ch == '(') {
                newStr += ')';
                continue;
            }
            
            newStr += '(';
        }
        
        return newStr;
    }
}