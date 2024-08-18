import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        br.close();
        
        // 다리를 건너기 전 대기하고 있는 트럭 Queue
        Queue<Integer> waits = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            waits.add(Integer.parseInt(st.nextToken()));
        }
        
        // 다리를 건너고 있는 트럭 Queue
        // 다리 길이만큼 0으로 초기화
        Queue<Integer> cross = new LinkedList<>();
        for(int i = 0; i < W; i++) {
            cross.add(0);
        }
        
        
        int time = 0;
        int totalWeight = 0;
        while(!cross.isEmpty()) {
            time += 1;
            
            // 건너고 있는 트럭들의 총 무게에서 다리를 건넌 트럭 무게 차감
            totalWeight -= cross.poll();
            
            // 대기하고 있는 트럭이 없는 경우
            if(waits.isEmpty()) {
                continue;
            }
            
            // 다리를 건너기 위해 대기하고 있는 트럭의 무게를 포함했을 때 최대중량을 넘지 않는 경우
            if(totalWeight + waits.peek() <= L) {
                int truck = waits.poll();
                totalWeight += truck;
                cross.add(truck);
                continue;
            }
            
            // 다리를 건너지 못하는 경우
            cross.add(0);
        }
        
        System.out.println(time);
    }
}