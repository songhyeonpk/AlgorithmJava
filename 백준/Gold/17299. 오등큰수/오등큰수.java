import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 수열 배열
        int[] numbers = new int[N];
        
        // 각 수의 개수를 담을 배열
        int[] counts = new int[1000001];
        
        // 결과 배열
        int[] result = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            numbers[i] = num;
            counts[num] += 1;
        }
        
        // 수열을 순회하면서 수의 인덱스를 저장하기 위한 스택
        Stack<Integer> idxStack = new Stack<>();
        for(int i = 0; i < N; i++) {
            // 이전 인덱스 수의 개수보다 현재 수의 개수가 많을 경우
            // 결과 배열의 이전 수 인덱스에 현재 수 저장
            while(!idxStack.isEmpty() && counts[numbers[idxStack.peek()]] < counts[numbers[i]]) {   
                result[idxStack.pop()] = numbers[i];
            }
            
            idxStack.push(i);
        }
        
        // 인덱스가 남아있는 경우 해당 수보다 많은 개수를 가진 수가 없으므로 -1 저장
        while(!idxStack.isEmpty()) {
            result[idxStack.pop()] = -1;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }
        
        System.out.println(sb);
    }
}