import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[] numbers;
    static int[] cards;
    static boolean[] visited;
    static Set<String> result = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        numbers = new int[N];
        cards = new int[K];
        visited = new boolean[N];
        
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        
        backtracking(0);
        System.out.println(result.size());
    }
    
    private static void backtracking(int depth) {
        if(depth == K) {
            // 카드에 적힌 숫자들로 새로운 수 만들기
            String number = "";
            for(int card : cards) {
                number += card + "";
            }
            
            result.add(number);
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                cards[depth] = numbers[i];
                backtracking(depth + 1);
                visited[i] = false;
            }
        }
    }
}