class Solution {
    public int solution(int[] numbers) {
        boolean[] check = new boolean[10];
        for(int number : numbers) {
            check[number] = true;
        }
        
        int answer = 0;
        for(int i = 0; i < 10; i++) {
            if(!check[i]) {
                answer += i;
            }
        }
        
        return answer;
    }
}