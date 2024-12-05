class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        // 변환된 결과        
        val answer = StringBuilder()
        for(alpha in s.toCharArray()) {
            // 다음 알파벳
            var next = alpha
            
            // 뒤로 이동한 수
            var cnt = 0
            while(cnt < index) {
                // 'a' ~ 'z' 까지의 소문자 알파벳 수식
                // 1씩 뒤로 이동
                next = 'a' + (next - 'a' + 1) % 26
                
                // next += 1
                // if(next == 'z') next = 'a'
                
                // 다음 알파벳이 skip 알파벳이 아닌 경우만 cnt 증가
                if(!skip.contains(next)) cnt += 1
            }
            
            answer.append(next)
        }
        
        return answer.toString()
    }
}