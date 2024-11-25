class Solution {
    fun solution(new_id: String): String {
        var answer = new_id
        
        // 1단계
        answer = chapter01(answer)
        
        // 2단계
        answer = chapter02(answer)
        
        // 3단계
        answer = chapter03(answer)
        
        // 4단계
        answer = chapter04(answer)
        
        // 5단계
        answer = chapter05(answer)
        
        // 6단계
        answer = chapter06(answer)
        
        // 7단계
        answer = chapter07(answer)
        
        return answer
    }
    
    // 대문자를 소문자로 치환
    private fun chapter01(newId: String): String {
        val result = StringBuilder()
        for(ch in newId) {
            if(ch.isUpperCase()) result.append(ch.lowercase())
            else result.append(ch)
        }
        
        return result.toString()
    }
    
    // 알파벳 소문자, 숫자, 빼기, 마침표, 밑줄, 마침표를 제외한 문자 제거
    private fun chapter02(newId: String): String {
        return newId.filter {
            it.isLowerCase() || it.isDigit() || it == '-' || it == '_' || it == '.'
        }
    }
    
    // 마침표가 2번 이상 연속된 부분을 하나의 마침표로 치환
    
    // 단순 표현식과 정규식 표현식
    // replace("..", ".") 와 replace("\\.{2,}".toRegex(), ".") 의 실행 차이
    // 첫번째 단순 표현식은 '.' 두개로 연속된 문자열 ".."이 "."로 치환되긴 하지만 한 번만 치환됨. 세 개 이상일 때 모두 치환되지 않음
    // 정규식 표현식은 '.' 두개로 연속된 문자열 ".."이 존재할 때 마다 "."로 모두 치환이 됨
    private fun chapter03(newId: String): String {
        return newId.replace("\\.{2,}".toRegex(), ".")
    }
    
    // 처음이나 끝의 마침표 제거
    private fun chapter04(newId: String): String {
        return newId.trim('.')
    }
    
    // 빈 문자열이라면 "a" 대입
    private fun chapter05(newId: String): String {
        return newId.ifEmpty { "a" }
    }
    
    // 길이가 16자 이상이면, 첫 15개의 문자를 제외하고 제거
    // 제거 후 끝의 마침표 제거
    private fun chapter06(newId: String): String {
        var result = newId
        if(result.length >= 16) result = newId.substring(0, 15)
        result = result.trimEnd('.')
        
        return result
    }
    
    // 길이가 2자 이하라면 new_id의 마지막 문자를 길이가 3이 될 때까지 끝에 추가
    private fun chapter07(newId: String): String {
        var result = newId
        while(result.length < 3) result += newId.last()
        
        return result
    }
}