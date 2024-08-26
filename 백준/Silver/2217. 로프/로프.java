import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 무게 배열
        int[] weights = new int[N];
        for(int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        
        // 무게를 기준으로 오름차순 정렬
        Arrays.sort(weights);
        
        // 들 수 있는 최대 중량을 제일 큰 중량으로 초기화
        int maxWeight = weights[N - 1];
        for(int i = N - 1; i >= 0; i--) {
            // 중량이 큰 루프부터 순회
            // 현재 루프의 무게와 연결된 루프의 개수로 최대 중량 계산
            int weight = weights[i] * (N - i);
            maxWeight = Math.max(maxWeight, weight);
        }
        
        System.out.println(maxWeight);
    }
}