class Solution {
    
    public int solution(int[] diffs, int[] times, long limit) {
        // 최소 숙련도, 최대 숙련도
        int minLevel = 1;
        int maxLevel = 100000;
        
        while(minLevel < maxLevel) {
            // 최소 숙련도와 최대 숙련도의 중간 값을 탐색할 숙련도로 지정
            int level = (minLevel + maxLevel) / 2;
            
            // 현재 숙련도로 퍼즐을 해결할 수 있을 경우, 최대 숙련도 값을 줄여 최종 숙련도의 범위를 줄임
            if(isGameResolve(diffs, times, level, limit)) {
                maxLevel = level;
            } else {    // 퍼즐을 해결할 수 없는 경우, 최소 숙련도의 값을 높여 최종 숙련도의 범위를 높임
                minLevel = level + 1;
            }
        }
        
        return maxLevel;
    }
    
    // 특정 레벨로 게임을 해결할 수 있는지 판별
    public static boolean isGameResolve(int[] diffs, int[] times, int level, long limit) {
        // 모든 퍼즐을 해결하는데 걸린 시간
        long result = 0;
        
        for(int i = 0; i < diffs.length; i++) {
            long time = 0;
            
            // 현재 퍼즐의 난이도보다 숙련도가 같거나 큰 경우
            // 현재 퍼즐의 해결 시간 = 현재 퍼즐을 해결하는데 걸린 시간
            if(diffs[i] <= level) {
                time += times[i];
            }
            
            // 현재 퍼즐의 난이도보다 숙련도가 작은 경우
            // (현재 퍼즐의 난이도 - 숙련도) * (현재 퍼즐의 해결시간 + 이전 퍼즐의 해결시간) + 현재 퍼즐의 해결시간 = 현재 퍼즐을 해결하는데 걸린 시간
            if(diffs[i] > level) {
                time += ((diffs[i] - level) * (times[i] + times[i - 1]) + times[i]);
            }
            
            result += time;
        }
        
        // 모든 퍼즐을 해결한 시간과 limit을 비교해 해결 여부 반환
        return result <= limit;
    }
}