class Solution {
    static boolean[] check;
    static int answer = Integer.MIN_VALUE;
    
    public int solution(int k, int[][] dungeons) {
        check = new boolean[dungeons.length];
        
        backtracking(0, 0, k, dungeons);
        return answer;
    }
    
    private static void backtracking(int depth, int cnt, int k, int[][] dungeons) {
        if(k <= 0 || depth == dungeons.length) {
            answer = Math.max(answer, cnt);
            return;
        }
        
        for(int i = 0; i < dungeons.length; i++) {
            int minF = dungeons[i][0];
            int consume = dungeons[i][1];
            if(!check[i]) {
                check[i] = true;
                
                if(k < minF) {
                    backtracking(depth + 1, cnt, k, dungeons);
                } else {
                    backtracking(depth + 1, cnt + 1, k - consume, dungeons);
                }   
                
                check[i] = false;
            }
        }
    }
}