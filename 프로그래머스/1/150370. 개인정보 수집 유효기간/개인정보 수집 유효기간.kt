class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        // 오늘 날짜 정보
        val todayArr = today.split(".")
        val todayYear = todayArr[0].toInt()
        val todayMonth = todayArr[1].toInt()
        val todayDay = todayArr[2].toInt()

        // 약관 정보 Map
        val termsMap: MutableMap<String, Int> = mutableMapOf()
        for(term in terms) {
            val termArr = term.split(" ")

            // 종류
            val kind = termArr[0]

            // 기간
            val period = termArr[1]

            termsMap[kind] = period.toInt()
        }

        val answer: MutableList<Int> = mutableListOf()
        for((idx, privacy) in privacies.withIndex()) {
            // 개인정보 날짜, 약관
            val privacyArr = privacy.split(" ")
            val privacyDate = privacyArr[0].split(".")
            val year = privacyDate[0].toInt()
            val month = privacyDate[1].toInt()
            val day = privacyDate[2].toInt()
            val kind = privacyArr[1]

            // 만료날짜
            var expiredYear = year + ((month + termsMap[kind]!!) / 12)
            var expiredMonth = (month + termsMap[kind]!!) % 12
            var expiredDay = day - 1
            
            // 만료일이 0일 경우 만료월 - 1, 만료일 28 초기화
            if(expiredDay == 0) {
                expiredMonth -= 1;
                expiredDay = 28
            }

            // 만료월이 0일 경우 만료년도 -1, 만료월 12 초기화
            if(expiredMonth == 0) {
                expiredYear -= 1;
                expiredMonth = 12
            }

            // 오늘 날짜가 만료날짜 이후인 경우
            if(todayYear > expiredYear ||
                (todayYear == expiredYear && todayMonth > expiredMonth) ||
                (todayYear == expiredYear && todayMonth == expiredMonth && todayDay > expiredDay)) {
                answer.add(idx + 1)
            }
        }

        return answer.toIntArray()
    }
}