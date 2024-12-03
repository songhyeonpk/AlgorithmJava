class Solution {
    
    fun solution(record: Array<String>): Array<String> {
        // 사용자 데이터를 저장할 Map (id : nickname)
        val usersMap: MutableMap<String, String> = mutableMapOf()
        
        // 사용자 기록을 저장할 List ( id, message )
        val records: MutableList<Pair<String, String>> = mutableListOf()
        
        for(rc in record) {
            val values = rc.split(" ")
            val state = values[0]
            val id = values[1]
            
            // 사용자가 채팅방을 나간 경우
            if(state == "Leave") {
                records.add(id to "님이 나갔습니다.")
            } else {    // 사용자가 닉네임 변경 혹은 채팅방을 들어온 경우
                val nickname = values[2]
                usersMap[id] = nickname
                
                // 사용자가 채팅방을 들어온 경우
                if(state == "Enter") {
                    records.add(id to "님이 들어왔습니다.")
                }
            }
        }
        
        // 사용자 기록 리스트를 "사용자 닉네임" + message 형식으로 반환
        return records.map { (id, message) ->
            "${usersMap[id]}$message"
        }.toTypedArray()
    }
}