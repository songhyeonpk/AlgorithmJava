import java.util.*;

class Solution {    
    public String solution(int[] numbers) {
        List<String> numberList = new ArrayList<>();
        for(int i = 0; i < numbers.length; i++) {
            numberList.add(numbers[i] + "");
        }
        
        Collections.sort(numberList, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                return Integer.parseInt(s2 + s1) - Integer.parseInt(s1 + s2);
            }
        });
        
        String answer = "";
        for(String number : numberList) {
            answer += number;
        }
        
        return answer.charAt(0) == '0' ? Integer.parseInt(answer) + "" : answer;
    }
    /*
    public static void permutation(int[] arr, int[] numbers, int depth) {
        if(depth == numbers.length) {
            String num = "";
            for(int i = 0; i < arr.length; i++) {
                num += arr[i] + "";
            }
            
            answer = Math.max(answer, Long.parseLong(num));
            return;
        }
        
        for(int i = 0; i < numbers.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = numbers[i];
                permutation(arr, numbers, depth + 1);
                visited[i] = false;
            }
        }
    } */
}