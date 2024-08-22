import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        br.close();
        
        // 물 세는 위치 배열
        int[] leakLocations = new int[N];
        for(int i = 0; i < N; i++) {
            leakLocations[i] = Integer.parseInt(st.nextToken());
        }
        
        // 위치를 오름차순으로 정렬
        Arrays.sort(leakLocations);
        
        // 현재 붙인 테이프의 가장 왼쪽 위치와 가장 오른쪽 위치
        double left_pos = 0.0;
        double right_pos = 0.0;
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            int location = leakLocations[i];
            int range = L - 1;
            
            // 물이 세는 위치가 테이프 범위 밖을 벗어날 경우
            // 테이프 개수 1 증가
            // 현재 붙인 테이프의 가장 왼쪽, 가장 오른쪽 위치 업데이트
            if(!(location >= left_pos && location <= right_pos)) {
                cnt += 1;
                left_pos = location - range - 0.5;
                right_pos = location + range + 0.5;
            }
        }
        
        System.out.println(cnt);
    }
}