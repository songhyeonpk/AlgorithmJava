class Solution {
    public int solution(int[][] sizes) {
        int width = 0;
        int height = 0;
        for(int i = 0; i < sizes.length; i++) {
            int w = sizes[i][0];
            int h = sizes[i][1];
            
            width = Math.max(width, Math.max(w, h));
            height = Math.max(height, Math.min(w, h));
        }
        
        return width * height;
    }
}