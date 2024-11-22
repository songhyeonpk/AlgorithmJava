class Solution {
    private val pattern = "%02d:%02d"
    
    fun solution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {
        var currentTime = skip(pos, op_start, op_end)
        
        commands.forEach { command ->
            currentTime = when(command) {
                "next" -> next(currentTime, video_len)
                "prev" -> prev(currentTime)
                else -> currentTime
            }
            
            // 이동한 후의 현재 재생 시간이 오프닝 범위 인지 확인 후 이동
            currentTime = skip(currentTime, op_start, op_end)
        }
        
        return currentTime
    }
    
    // 오프닝 건너뛰기
    private fun skip(pos: String, opStart: String, opEnd: String): String {
        val (curMin, curSec) = pos.split(":").map { it.toInt() }
        val (startMin, startSec) = opStart.split(":").map { it.toInt() }
        val (endMin, endSec) = opEnd.split(":").map { it.toInt() }
        
        // 현재 시간이 오프닝 시간 범위라면
        // 오프닝 마지막 시간 지점으로 이동
        if((curMin > startMin || (curMin == startMin && curSec >= startSec)) &&
                (curMin < endMin || (curMin == endMin && curSec <= endSec))) {
            return opEnd
        } 
        
        return pos
    }
    
    // 10초 이후로 이동
    private fun next(pos: String, videoLen: String): String {
        val (curMin, curSec) = pos.split(":").map { it.toInt() }
        val (endMin, endSec) = videoLen.split(":").map { it.toInt() }
        var newMinute = curMin + (curSec + 10).div(60)
        var newSeconds = (curSec + 10).rem(60)
        
        // 10초 이후의 시간이 비디오 재생 마지막 시간보다 크다면
        // 비디오 재생 마지막 시간으로 이동
        if(newMinute > endMin ||
                (newMinute == endMin && newSeconds > endSec)) {
            return videoLen
        }
        
        return pattern.format(newMinute, newSeconds)
    }
    
    // 10초 이전으로 이동
    private fun prev(pos: String): String {
        val (curMin, curSec) = pos.split(":").map { it.toInt() }
        
        // 현재 초가 10보다 작으면 현재 분 - 1
        var newMinute = curMin - if (curSec < 10) 1 else 0
        var newSeconds = (curSec - 10 + 60).rem(60)
        
        // 만약 10초 이전의 시간이 00분보다 작다면 00분 00초로 초기화
        if(newMinute < 0) {
            newMinute = 0
            newSeconds = 0
        }
        
        return pattern.format(newMinute, newSeconds)
    }
}