import java.io.*;
import java.util.*;

public class Main {
    static int L;
    static int C;
    static String[] password;
    static String[] words;
    static List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");
    static List<String> passwordList = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        password = new String[L];
        words = new String[C];
        
        st = new StringTokenizer(br.readLine());
        br.close();
        
        for(int i = 0; i < C; i++) {
            words[i] = st.nextToken();
        }
        
        // 단어 배열을 사전순으로 정렬
        Arrays.sort(words);
        
        backtracking(0, 0);
        
        StringBuilder sb = new StringBuilder();
        for(String password : passwordList) {
            sb.append(password).append("\n");
        }
        
        System.out.println(sb);
    }
    
    private static void backtracking(int depth, int start) {
        if(depth == L) {
            // 모음, 자음 갯수
            int vowelCnt = 0;
            int consCnt = 0;
            String result = "";
            for(int i = 0; i < L; i++) {
                result += password[i];
                if(vowels.contains(password[i])) {
                    vowelCnt += 1;
                    continue;
                }
                
                consCnt += 1;
            }
            
            // 모음 갯수가 최소 한개 이상 && 자음 갯수가 최소 두개 이상
            if(vowelCnt > 0 && consCnt > 1) {
                if(!passwordList.contains(result)) {
                    passwordList.add(result);
                }
            }
            
            return;
        }
        
        for(int i = start; i < C; i++) {
            password[depth] = words[i];
            backtracking(depth + 1, i + 1);
        }
    }
}