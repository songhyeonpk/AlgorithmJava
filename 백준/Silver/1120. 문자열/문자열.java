import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String A = st.nextToken();
        String B = st.nextToken();
        
        int answer = Integer.MAX_VALUE;
        
        // B문자열 길이 - A문자열 길이 차이만큼 B문자열 이동하면서 검사
        for(int i = 0; i <= B.length() - A.length(); i++) {
            int cnt = 0;
            for(int j = 0; j < A.length(); j++) {
                // A 문자열의 문자는 고정하고 B 문자열의 문자를 한칸씩 이동하면서 비교
                // 틀린 경우 cnt 증가
                if(A.charAt(j) != B.charAt(j + i)) {
                    cnt += 1;
                }
            }
            
            answer = Math.min(answer, cnt);
        }
        
        System.out.println(answer);
    }
}