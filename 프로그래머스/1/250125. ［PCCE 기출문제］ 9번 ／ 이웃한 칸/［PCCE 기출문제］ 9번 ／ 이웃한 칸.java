class Solution {
    public int solution(String[][] board, int h, int w) {
        int[] dh = {0, 0, -1, 1};
        int[] dw = {-1, 1, 0, 0};
        
        int answer = 0;
        String color = board[h][w];
        
        for(int i = 0; i < 4; i++) {
            int nextHeight = h + dh[i];
            int nextWidth = w + dw[i];
            
            if(nextHeight >= 0 && nextHeight < board.length 
              && nextWidth >= 0 && nextWidth < board.length) {
                if(board[nextHeight][nextWidth].equals(color)) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}