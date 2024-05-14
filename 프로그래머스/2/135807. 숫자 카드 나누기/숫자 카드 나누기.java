class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int aGcd = arrayA[0];
        int bGcd = arrayB[0];
        for(int i = 1; i < arrayA.length; i++) {
            aGcd = gcd(Math.min(aGcd, arrayA[i]), Math.max(aGcd, arrayA[i]));
            bGcd = gcd(Math.min(bGcd, arrayB[i]), Math.max(bGcd, arrayB[i]));
        }

        boolean aCheck = true;
        boolean bCheck = true;
        for(int i = 0; i < arrayA.length; i++) {
            if(arrayA[i] % bGcd == 0) {
                aCheck = false;
            }
            
            if(arrayB[i] % aGcd == 0) {
                bCheck = false;
            }
        }
        
        if(!aCheck && !bCheck) {
            answer = 0;
        } else if(!aCheck && bCheck) {
            answer = aGcd;
        } else if(aCheck && !bCheck){
            answer = bGcd;
        } else {
            answer = Math.max(aGcd, bGcd);
        }
        
        return answer;
    }
    
    private static int gcd(int min, int max) {
        int val = max % min;
        if(val == 0) {
            return min;
        }
        
        return gcd(val, min);
    }
}