import java.io.*;
import java.util.*;

public class Main {
    static String[] strArr = new String[3];
    static List<String> words = new ArrayList<>();
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        br.close();
        
        backtracking(0, word);
        
        // 사전순 정렬
        Collections.sort(words);
        
        System.out.println(words.get(0));
    }
    
    private static void backtracking(int depth, String word) {
        // 두 부분으로 나눈 후 남은 단어를 세번째 부분 단어로 저장
        if(depth == 2) {
            strArr[2] = word;
            
            String newWord = "";
            for(int i = 0; i < 3; i++) {
                String part = strArr[i];
                
                for(int j = part.length() - 1; j >= 0; j--) {
                    newWord += part.charAt(j);
                }
            }
            
            words.add(newWord);
            return;
        }
        
        for(int i = 1; i < word.length(); i++) {
            String partStr = word.substring(0, i);
            String remainingStr = word.substring(i);
            strArr[depth] = partStr;
            backtracking(depth + 1, remainingStr);
        }
    }
}