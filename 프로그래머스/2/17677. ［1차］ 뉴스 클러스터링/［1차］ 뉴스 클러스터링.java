import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int num = 65536;
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        
        Map<String, Integer> A = set(str1);
        Map<String, Integer> B = set(str2);
        
        if(A.isEmpty() && B.isEmpty()) {
            return num;
        }
        
        int intersection = 0;
        int union = 0;
        
        // A 집합의 원소 기준으로 먼저 다중집합의 교집합, 합집합 개수 구하기
        for(Map.Entry<String, Integer> entry : A.entrySet()) {
            String element = entry.getKey();
            int cnt = entry.getValue();
            
            if(!B.containsKey(element)) {
                union += cnt;
                continue;
            }
            
            intersection += Math.min(cnt, B.get(element));
            union += Math.max(cnt, B.get(element));
        }
        
        // B의 집합 원소를 기준으로 합집합 개수 추가
        for(Map.Entry<String, Integer> entry : B.entrySet()) {
            if(!A.containsKey(entry.getKey())) {
                union += entry.getValue();
            }
        }
            
        int answer = (int) (((double) intersection / (double) union) * num);

        return answer;
    }
    
    private static Map<String, Integer> set(String str) {
        Map<String, Integer> map = new HashMap<>();
        int first = 0;
        int last = 2;
        while(last <= str.length()) {
            String element = str.substring(first, last);
            boolean check = true;
            
            for(int i = 0; i < 2; i++) {
                char ch = element.charAt(i);
                if(ch < 'A' || ch > 'Z') {
                    check = false;
                    break;
                }
            }
            
            if(check) {
                map.put(element, map.getOrDefault(element, 0) + 1);
            }
            
            first += 1;
            last += 1;
        }
        
        return map;
    }
}