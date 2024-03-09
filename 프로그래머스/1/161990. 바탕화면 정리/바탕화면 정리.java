class Solution {
    public int[] solution(String[] wallpaper) {    
        char[][] arr = new char[wallpaper.length][wallpaper[0].length()];
        for(int i = 0; i < wallpaper.length; i++) {
            for(int j = 0; j < wallpaper[i].length(); j++) {
                char ch = wallpaper[i].charAt(j);
                arr[i][j] = ch;
            }
        }
        
        int lux = Integer.MAX_VALUE;
        int luy = Integer.MAX_VALUE;
        int rdx = Integer.MIN_VALUE;
        int rdy = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                char ch = arr[i][j];
                
                if(ch == '.') {
                    continue;
                }
                
                if(lux > i) {
                    lux = i;
                }
                
                if(luy > j) {
                    luy = j;
                }
                
                if(rdx < i) {
                    rdx = i;
                }
                
                if(rdy < j) {
                    rdy = j;
                }
            }
        }
        
        return new int[] {lux, luy, rdx + 1, rdy + 1};
    }
}