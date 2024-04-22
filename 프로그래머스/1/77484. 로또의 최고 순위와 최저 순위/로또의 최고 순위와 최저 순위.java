import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        int[] answer = new int[2];
        List<Integer> winNumList = new ArrayList<>();
        for(int num : win_nums) {
            winNumList.add(num);
        }
        
        int zero = 0;
        int cnt = 0;
        for(int i = 0; i < lottos.length; i++) {
            if(lottos[i] == 0) {
                zero++;
            }
            
            if(winNumList.contains(lottos[i])) {
                cnt++;
            }
        }
        
        answer[0] = rank[cnt + zero];
        answer[1] = rank[cnt];
        
        return answer;
    }
}