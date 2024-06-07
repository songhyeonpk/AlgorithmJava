import java.util.*;

class Solution {
    static Set<Integer> result = new HashSet<>();
    
    public int solution(int[] elements) {
        for(int i = 0; i < elements.length; i++) {
            for(int j = 0; j < elements.length; j++) {
                sum(0, j, i + 1, new int[i + 1], elements);
            }
        }
        
        int answer = result.size();
        return answer;
    }
    
    private static void sum(int depth, int start, int len, int[] arr, int[] elements) {
        if(depth == len) {
            int sum = 0;
            for(int i = 0; i < depth; i++) {
                sum += arr[i];
            }
            result.add(sum);
            
            return;
        }
        
        if(start >= elements.length) {
            sum(depth, 0, len, arr, elements);
            return;
        }
        
        arr[depth] = elements[start];
        sum(depth + 1, start + 1, len, arr, elements);
    }
}