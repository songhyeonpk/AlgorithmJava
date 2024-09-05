import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String search = br.readLine();
        br.close();
        
        // 중복되지 않고 검색 문자열과 일치하는 문자열의 수
        int result = 0;
        
        // 현재 인덱스
        int curIdx = 0;
        while(true) {
            
            // 전체 문자열에서 시작 인덱스로부터 검색할 문자열 탐색
            // 존재할 경우 문자열의 첫번째 인덱스 반환
            // 존재하지 않으면 -1 반환
            int findIdx = str.indexOf(search, curIdx);
            if(findIdx < 0) break;
            
            result += 1;
            
            // 찾은 인덱스 + 검색할 문자열의 길이 값을 현재 인덱스로 초기화
            curIdx = findIdx + search.length();
        }
        
        System.out.println(result);
    }
}