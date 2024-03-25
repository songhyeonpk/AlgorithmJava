import java.util.*;

class Solution {
    public static List<Integer> numberList = new ArrayList<>();
    public static boolean[] visited;
    public static boolean[] prime;
    
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        char[] arr = new char[numbers.length()];
        
        for(int i = 0; i < numbers.length(); i++) {
            backtracking(numbers, arr, 0, i + 1);
        }
        
        Collections.sort(numberList);
        int maxNum = numberList.get(numberList.size() - 1);
        prime = new boolean[maxNum + 1];
        makePrime(maxNum);
        
        int answer = 0;
        for(int num : numberList) {
            if(!prime[num]) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void backtracking(String str, char[] arr, int depth, int r) {
        if(depth == r) {
            String numStr = "";
            for(int i = 0; i < r; i++) {
                numStr += arr[i] + "";
            }
            
            int num = Integer.parseInt(numStr);
            if(num > 1 && !numberList.contains(num)) {
                numberList.add(num);
            }
            
            return;
        }
        
        for(int i = 0; i < str.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = str.charAt(i);
                backtracking(str, arr, depth + 1, r);
                visited[i] = false;
            }
        }
    }
    
    public static void makePrime(int n) {
        for(int i = 2; i <= Math.sqrt(n); i++) {
            for(int j = i + i; j <= n; j += i) {
                if(prime[j]) {
                    continue;
                }
                
                prime[j] = true;
            }
        }
    }
}