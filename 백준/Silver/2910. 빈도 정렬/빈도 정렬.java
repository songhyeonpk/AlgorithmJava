import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        Map<Integer, int[]> map = new HashMap<>();
        for(int i = 1; i<= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            
            // 수가 이미 존재할 경우
            // 빈도 수만 증가
            if(map.containsKey(num)) {
                int[] arr = map.get(num);
                arr[0] += 1;
                continue;
            }
            
            // 빈도 수를 1로 초기화하고 순서를 지정하고 map에 추가
            map.put(num, new int[] {1, i});
        }
        br.close();
        
        List<Map.Entry<Integer, int[]>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, int[]>>() {
            @Override
            public int compare(Map.Entry<Integer, int[]> o1, Map.Entry<Integer, int[]> o2) {
                // 빈도의 수가 같다면 순서를 기준으로 오름차순 정렬
                if(o1.getValue()[0] == o2.getValue()[0]) {
                    return o1.getValue()[1] - o2.getValue()[1];
                }
                
                // 빈도의 수를 기준으로 내림차순 정렬
                return o2.getValue()[0] - o1.getValue()[0];
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, int[]> entry : list) {
            for(int i = 0; i < entry.getValue()[0]; i++) {
                sb.append(entry.getKey()).append(" ");
            }
        }
        
        System.out.println(sb);
    }
}