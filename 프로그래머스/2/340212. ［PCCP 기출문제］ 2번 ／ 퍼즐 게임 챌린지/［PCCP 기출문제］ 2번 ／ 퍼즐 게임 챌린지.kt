class Solution {
    
    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        // 최소 숙련도, 최대 숙련도 초기화
        var minLevel = 1
        var maxLevel = 100000
        
        while(minLevel < maxLevel) {
            // 최소 숙련도와 최대 숙련도의 중간 숙련도 
            val level = (minLevel + maxLevel) / 2
            
            // 현재 숙련도로 퍼즐을 해결할 수 있는 경우, 최대 숙련도 값을 줄여 최종 숙련도 범위를 줄임
            if(isGameResolve(diffs, times, level, limit)) {
                maxLevel = level
            } else {    // 퍼즐을 해결할 수 없는 경우, 최소 숙련도 값을 높여 최종 숙련도 범위를 높임
                minLevel = level + 1
            }
        }
        
        return maxLevel
    }
    
    private fun isGameResolve(diffs: IntArray, times: IntArray, level: Int, limit: Long): Boolean {
        // 모든 퍼즐을 해결하는 데 걸린 총 시간
        var result: Long = 0
        
        for((idx, diff) in diffs.withIndex()) {
            var time: Long = 0
            
            // 현재 퍼즐의 난이도보다 숙련도가 같거나 클 경우
            if(diff <= level) {
                time += times[idx]
            }
            
            // 현재 퍼즐의 난이도보다 숙련도가 작을 경우
            if(diff > level) {
                time += ((diff - level) * (times[idx] + times[idx - 1])) + times[idx]
            }
            
            result += time
        }
        
        // 모든 퍼즐의 해결 여부 반환
        return result <= limit
    }
}