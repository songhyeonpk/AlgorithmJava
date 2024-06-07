import java.util.*;

class Solution {
    public long solution(String expression) {
        // 연산자 우선순위 배열
        char[][] priority = {{'*', '+', '-'}, {'*', '-', '+'},
                               {'+', '*', '-'}, {'+', '-', '*'},
                               {'-', '*', '+'}, {'-', '+', '*'}};
        
        long answer = Long.MIN_VALUE;
        for(int i = 0; i < 6; i++) {
            char[] operations = priority[i];
            
            // 주어진 수식을 숫자, 연산자로 각각 분리해 List에 저장
            List<String> expList = new ArrayList<>();
            int start = 0;
            for(int j = 0; j < expression.length(); j++) {
                char ch = expression.charAt(j);
                if(ch == '*' || ch == '+' || ch == '-') {
                    expList.add(expression.substring(start, j));
                    expList.add(ch + "");
                    start = j + 1;
                }
            }
            expList.add(expression.substring(start, expression.length()));
            
            // 연산자 우선순위를 기준으로 결과 구하기
            for(char operation : operations) {
                
                // 수식에 해당 연산자가 존재하지 않을 때까지 반복 수행
                while(expList.contains(operation + "")) {
                    
                    // 수식에서 해당 연산자의 가장 첫 인덱스 값 조회
                    int idx = expList.indexOf(operation + "");

                    long first = Long.parseLong(expList.get(idx - 1));
                    long second = Long.parseLong(expList.get(idx + 1));
                    long result = first * second;
                    
                    if(operation == '+') {
                        result = first + second;
                    }
                    
                    if(operation == '-') {
                        result = first - second;
                    }
                    
                    // 수식을 연산된 결과로 수정
                    expList.set(idx - 1, Long.toString(result));
                    for(int j = 0; j < 2; j++) {
                        expList.remove(idx);
                    }
                }
            }
            
            long result = Math.abs(Long.parseLong(expList.get(0)));
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
}