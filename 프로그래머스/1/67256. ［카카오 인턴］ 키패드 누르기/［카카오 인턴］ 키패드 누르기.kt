class Solution {
    
    private val keypad = arrayOf(
        arrayOf("1", "2", "3"),
        arrayOf("4", "5", "6"),
        arrayOf("7", "8", "9"),
        arrayOf("*", "0", "#")
    )
    private val dx = arrayOf(-1, 1, 0, 0)
    private val dy = arrayOf(0, 0, -1, 1)
    
    // 현재 왼쪽, 오른쪽 손가락 위치
    private var curLeft = Pair(3, 0)
    private var curRight = Pair(3, 2)
    
    private val result = StringBuilder()
    
    fun solution(numbers: IntArray, hand: String): String {
        for(number in numbers) {
            // 키패드 숫자 위치
            var row = (number - 1) / 3
            var col = (number - 1) % 3
            
            // 가운데 숫자
            if(number == 0 || number % 3 == 2) {
                if(number == 0) {
                    row = 3
                    col = 1
                }
                
                center(number, row, col, hand)
            }
            
            // 왼쪽 숫자
            if(number % 3 == 1) {
                left(row, col)
            }
            
            // 오른쪽 숫자
            if(number != 0 && number % 3 == 0) {
                right(row, col)
            }
        }
        
        return result.toString()
    }
    
    // 키패드 가운데 숫자
    private fun center(number: Int, row: Int, col: Int, hand: String) {
        val leftDist = bfs(curLeft.first, curLeft.second, number.toString())
        val rightDist = bfs(curRight.first, curRight.second, number.toString())
        
        if(leftDist < rightDist ||
                (leftDist == rightDist) && hand == "left") {
            left(row, col)
        } else {
            right(row, col)
        }
    }
    
    // 키패드 왼쪽 숫자
    private fun left(row: Int, col: Int) {
        curLeft = Pair(row, col)
        result.append("L")
    }
    
    // 키패드 오른쪽 숫자
    private fun right(row: Int, col: Int) {
        curRight = Pair(row, col)
        result.append("R")
    }
    
    // 현재 위치에서 다음 위치까지 거리 계산
    private fun bfs(x: Int, y: Int, number: String): Int {
        // 2차원 방문 체크 배열
        val visited = Array(keypad.size) { BooleanArray(keypad[0].size) }
        visited[x][y] = true
        
        // 경로 Queue
        // 시작 x 좌표, 시작 y 좌표, 처음 거리
        val queue: ArrayDeque<Triple<Int, Int, Int>> = ArrayDeque()
        queue.add(Triple(x, y, 0))
        
        while(queue.isNotEmpty()) {
            val (x, y, dist) = queue.removeFirst()
            if(keypad[x][y] == number) return dist
            
            for(i in 0..3) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                
                if(isInRange(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.add(Triple(nx, ny, dist + 1))
                }
            }
        }
        
        return -1
    }
    
    private fun isInRange(x: Int, y: Int): Boolean {
        return x >= 0 && x < 4 && y >= 0 && y < 3
    }
}