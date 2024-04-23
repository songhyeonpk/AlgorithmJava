import java.util.*;

class Solution {
    static class Stage {
        int n;
        double fr;
        
        Stage(int n, double fr) {
            this.n = n;
            this.fr = fr;
        }
    }
    
    public int[] solution(int N, int[] stages) {
        Stage[] result = new Stage[N];
        for(int i = 0; i < N; i++) {
            int challengeCnt = 0;
            int nonClearCnt = 0;
            for(int j = 0; j < stages.length; j++) {
                if(i + 1 <= stages[j]) {
                    if(i + 1 >= stages[j]) {
                        nonClearCnt += 1;
                    }
                    challengeCnt += 1;
                }
            }
            
            double fr = challengeCnt == 0 ? 0.0 : (double)nonClearCnt / challengeCnt;
            result[i] = new Stage(i + 1, fr);
        }
     
        // 실패율을 기준으로 내림차순 정렬
        Arrays.sort(result, new Comparator<Stage>() {
            @Override
            public int compare(Stage s1, Stage s2) {
                if(s1.fr == s2.fr) {
                    return s1.n - s2.n;
                }
                
                if(s1.fr > s2.fr) {
                    return -1;
                }
                
                return 1;
            }
        });
        
        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
            answer[i] = result[i].n;
        }
        
        return answer;
    }
}