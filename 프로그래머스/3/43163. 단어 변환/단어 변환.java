import java.util.*;

class Solution {  
    static class Word {
        String name;
        int cnt;
        
        Word(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        List<String> wordList = Arrays.asList(words);
        if(!wordList.contains(target)) return 0;
        
        int answer = bfs(begin, target, words);
        return answer;
    }
    
    public static int bfs(String begin, String target, String[] words) {
        Queue<Word> queue = new LinkedList<>();
        queue.offer(new Word(begin, 0));
        
        while(!queue.isEmpty()) {
            Word word = queue.poll();
            String name = word.name;
            int cnt = word.cnt;
            
            if(name.equals(target)) {
                return cnt;
            }
            
            for(int i = 0; i < words.length; i++) {
                String str = words[i];
                int changeCnt = 0;
                for(int j = 0; j < str.length(); j++) {
                    if(name.charAt(j) != str.charAt(j)) changeCnt++;
                }
                
                if(changeCnt == 1) queue.offer(new Word(str, cnt + 1));
            }
        }
        
        return 0;
    }
    
    /*
    public static int dfs(String word, String target, String[] words, int cnt, int depth) {
        if(depth >= words.length) {
            return Integer.MAX_VALUE;
        }
        
        if(word.equals(target)) {
            return cnt;
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++) {
            String str = words[i];
            int changeCnt = 0;
            for(int j = 0; j < word.length(); j++) {
                if(word.charAt(j) != str.charAt(j)) {
                    changeCnt++;
                }
            }
            
            if(changeCnt == 1) answer = Math.min(answer, dfs(str, target, words, cnt + 1, depth + 1));
        }
        
        return answer;
    }
    */
}