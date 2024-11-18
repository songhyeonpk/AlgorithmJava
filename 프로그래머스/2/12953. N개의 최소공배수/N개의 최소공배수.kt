class Solution {
    fun solution(arr: IntArray): Int {
        return arr.reduce { acc, num -> lcm(acc, num) }
    }
    
    // 최대 공약수 HDC
    fun gcd(min: Int, max: Int): Int {
        return if (max == 0) min else gcd(max, min % max)
    }
    
    // 최소 공배수 LMC
    fun lcm(min: Int, max: Int): Int {
        return min / gcd(min, max) * max 
    }
}