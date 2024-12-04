class Solution {
    
    // 과제 클래스
    class Task(
        val name: String,
        val startTime: Int,
        var playTime: Int
    )
    
    fun solution(plans: Array<Array<String>>): Array<String> {
        // 과제 목록
        val tasks: MutableList<Task> = mutableListOf()
        for(plan in plans) {
            val name = plan[0]
            val startTime = convertToMinute(plan[1].split(":"))
            val playTime = plan[2].toInt()
            
            tasks.add(Task(name, startTime, playTime))
        }
        
        // 과제 목록을 시작 시간을 기준으로 오름차순 정렬
        tasks.sortBy { it.startTime }
        
        // 진행할 과제 목록
        val progressList: MutableList<Task> = mutableListOf()
        
        // 결과 (끝난 과제 목록)
        val answer: MutableList<String> = mutableListOf()
        
        // 시간
        var time = 0
        for(curTask in tasks) {
            // 과제를 수행할 수 있는 시간
            var performTime = curTask.startTime - time
            
            // 진행할 과제가 있고 남는 시간이 존재할 경우
            while(progressList.isNotEmpty() && performTime > 0) {
                // 가장 최근 진행중인 과제
                val latest = progressList.removeLast()
                
                // 진행중인 과제를 끝낼 수 있는 경우
                if(latest.playTime < performTime) {
                    performTime -= latest.playTime
                    answer.add(latest.name)
                } 
                // 진행중인 과제를 끝낼 수 없는 경우
                else {    
                    // 과제 실행 시간 - 수행할 수 있는 시간
                    latest.playTime -= performTime
                    performTime = 0
                    
                    // 과제의 실행 시간이 아직 남은 경우
                    // 다시 진행목록에 추가
                    if(latest.playTime != 0) {
                        progressList.add(latest)
                        continue
                    }
                    
                    answer.add(latest.name)
                }
            }
            
            // 과제 진행, 시간을 현재 과제 시작시간으로 초기화
            progressList.add(curTask)
            time = curTask.startTime
        }
        
        // 아직 진행할 과제가 있는 경우
        // 가장 최근의 과제부터 실행
        while(progressList.isNotEmpty()) {
            answer.add(progressList.removeLast().name)
        }
        
        return answer.toTypedArray()
    }
    
    // 분 단위로 시간을 변환
    private fun convertToMinute(times: List<String>): Int {
        val hour = times[0].toInt() * 60
        val minute = times[1].toInt()
        
        return hour + minute
    }
}