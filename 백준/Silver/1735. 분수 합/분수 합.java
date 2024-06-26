import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int an = Integer.parseInt(st.nextToken());
        int ad = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int bn = Integer.parseInt(st.nextToken());
        int bd = Integer.parseInt(st.nextToken());
        br.close();
        
        // 두 수의 최대공약수
        int gcd = gcd(Math.min(ad, bd), Math.max(ad, bd));
        
        // 두 수의 최소공배수
        int lcm = (ad * bd) / gcd;
        
        int n = ((lcm / ad) * an) + ((lcm / bd) * bn);
        
        // 기약분수로 만들기 위한 결과 분자와 분모 값의 최대 공약수 구하기 
        int resultGcd = gcd(Math.min(n, lcm), Math.max(n, lcm));
        
        String result = (n / resultGcd) + " " + (lcm / resultGcd);
        System.out.println(result);
    }
    
    private static int gcd(int min, int max) {
        int value = max % min;
        if(value == 0) {
            return min;
        }
        
        return gcd(value, min);
    }
}