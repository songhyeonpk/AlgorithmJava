import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        int[] nums = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        // 오름차순 정렬
        Arrays.sort(nums);
        
        int cnt = 0;
        int start = 0;
        int end = N - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
            
            // 두 수를 더한 값이 M보다 클 경우
            // 마지막 인덱스 1 감소
            if(sum > M) {
                end -= 1;
            }
            
            // M과 같거나 작을 경우
            // 시작 인덱스 1 증가
            if(sum <= M) {
                
                // M과 같을 경우 cnt 값 1 증가
                if(sum == M) {
                    cnt += 1;
                }
                
                start += 1;
            }
        }
        
        System.out.println(cnt);
    }
}