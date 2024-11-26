class Solution {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        
        // 신고결과
        val reportResultMap: MutableMap<String, MutableList<String>> = mutableMapOf()
        
        // 신고횟수
        val reportCountMap: MutableMap<String, Int> = mutableMapOf()
        
        for(rp in report) {
            val (to, from) = rp.split(" ")
            
            // 신고결과 Map에 신고한 유저의 id가 없는 경우, key 생성 및 MutableList 초기화
            val list = reportResultMap.getOrPut(to) { mutableListOf() }
            
            // 신고당한 회원이 존재하지 않는 경우 (중복 신고 방지)
            if(!list.contains(from)) {
                list.add(from)
                reportCountMap[from] = reportCountMap.getOrDefault(from, 0) + 1
            }
        }
        
        val answer = IntArray(id_list.size)
        for((idx, id) in id_list.withIndex()) {
            var count = 0
            
            // 유저의 신고 결과 목록을 순회하면서 k번 이상 신고당했을 경우 처리 결과 횟수 + 1
            for(reportedUser in reportResultMap[id] ?: emptyList()) {
                if((reportCountMap[reportedUser] ?: 0) >= k) count += 1
            }
            
            answer[idx] = count
        }
        
        return answer
    }
}