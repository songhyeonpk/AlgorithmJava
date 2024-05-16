class Solution {
    public int[] solution(int brown, int yellow) {
        int totalCnt = brown + yellow;
        int[] answer = new int[2];
        for(int i = 1; i <= totalCnt; i++) {
            if(totalCnt % i != 0) {
                continue;
            }
            
            int row = totalCnt / i;
            int col = i;
            int yellowCnt = totalCnt - ((row * 2) + (col -2) * 2);
            if(row >= col && yellowCnt == yellow) {
                answer[0] = row;
                answer[1] = col;
                break;
            }
        }
        
        return answer;
    }
}