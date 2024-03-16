import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, new Comparator<int[]>(){
           @Override
            public int compare(int[] a1, int[] a2) {
                if(a1[col - 1] == a2[col - 1]) {
                    return a2[0] - a1[0];
                }
                
                return a1[col - 1] - a2[col - 1];
            }
        });
        
        int answer = 0;
        for(int i = row_begin - 1; i < row_end; i++) {
            int[] arr = data[i];
            int sum = 0;
            for(int j = 0; j < arr.length; j++) {
                sum += (arr[j] % (i + 1));
            }
            
            answer = answer ^ sum;
        }
        
        return answer;
    }
}