import java.util.*;

class Solution {
    public static int h;
    public static int w;
    public static char[][] arr;
    
    public int[] solution(String[] park, String[] routes) {
        h = park.length;
        w = park[0].length();
        
        arr = new char[park.length][park[0].length()];
        for(int i = 0; i < park.length; i++) {
            arr[i] = park[i].toCharArray();
        }
        
        int dh = 0;
        int dw = 0;
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] == 'S') {
                    dh = i;
                    dw = j;
                    break;
                }
            }
        }
        
        for(String route : routes) {
            String d = route.split(" ")[0];
            int n = Integer.parseInt(route.split(" ")[1]);
            
            if(d.equals("N")) {
                if(validateIndex(dh - n, dw) && move(dh - n, dh, dw, dw)) {
                    dh -= n;
                }
            } else if(d.equals("S")) {
                if(validateIndex(dh + n, dw) && move(dh, dh + n, dw, dw)) {
                    dh += n;
                }
            } else if(d.equals("W")) {
                if(validateIndex(dh, dw - n) && move(dh, dh, dw - n, dw)) {
                    dw -= n;
                }
            } else {
                if(validateIndex(dh, dw + n) && move(dh, dh, dw, dw + n)) {
                    dw += n;
                }
            }
        }
        
        return new int[] {dh, dw};
    }
    
    public static boolean move(int sh, int eh, int sw, int ew) {
        for(int i = sh; i <= eh; i++) {
            for(int j = sw; j <= ew; j++) {
                if(arr[i][j] == 'X') {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static boolean validateIndex(int nh, int nw) {
        return nh >= 0 && nh < h && nw >= 0 && nw < w;
    }
    
}