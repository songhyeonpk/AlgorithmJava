import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static boolean[] visited;
    static Set<Integer> nums = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        
        for(int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, i);
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 최종 결과 수 저장
        sb.append(nums.size());
        sb.append("\n");
        
        List<Integer> results = new ArrayList<>(nums);
        
        // 오름차순 정렬
        Collections.sort(results);
        
        for(int num : results) {
            sb.append(num);
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    private static boolean dfs(int startIdx, int n) {
        int next = arr[n];
        
        // 사이클이 발생하고 다음 인덱스 값이 시작 인덱스와 같은 경우
        if(visited[next] && next == startIdx) {
            nums.add(n);
            return true;
        }
        
        // 다음 인덱스를 방문하지 않은 경우
        if(!visited[next]) {
            visited[next] = true;
            
            // 시작 인덱스와 다음 인덱스의 값을 비교하며 사이클 발생 여부 및 값 비교
            if(dfs(startIdx, next)) nums.add(n);
        }
        
        return false;
    }
}