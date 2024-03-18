class Solution {
    public static int cnt = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0, "+");
        dfs(numbers, target, 0, 0, "-");
        
        return cnt / 2;
    }
    
    public static void dfs(int[] numbers, int target, int start, int sum, String str) {
        if(start >= numbers.length) {
            if(target == sum) {
                cnt++;
            }
            return;
        }
        
        int num = str.equals("+") ? 0 + numbers[start] : 0 - numbers[start];
        dfs(numbers, target, start + 1, sum + num, "+");
        dfs(numbers, target, start + 1, sum + num, "-");
    }
}