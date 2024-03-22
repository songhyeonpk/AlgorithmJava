import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        int answer = n - lost.length;
        
        // 여벌 체육복을 가져온 학생이 체육복을 도난당했을 경우를 대비해 적절성 검사
        for(int i = 0; i < lost.length; i++) {
            for(int j = 0; j < reserve.length; j++) {
                if(lost[i] == reserve[j]) {
                    answer++;
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        
        for(int i = 0; i < lost.length; i++) {
            if(lost[i] == -1) {
                continue;
            }
            
            for(int j = 0; j < reserve.length; j++) {
                if(reserve[j] == -1) {
                    continue;
                }
                
                if(reserve[j] == lost[i] - 1 || reserve[j] == lost[i] + 1) {
                    answer++;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        
        return answer;
    }
}