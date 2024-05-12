import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] parents;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        visited = new boolean[N + 1];
        
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        br.close();
        bfs(1);
        for(int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }
    
    static void bfs(int startV) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startV);
        visited[startV] = true;
        
        while(!queue.isEmpty()) {
            int v = queue.poll();
            
            for(int node : graph.get(v)) {
                if(!visited[node]) {
                    queue.offer(node);
                    visited[node] = true;
                    parents[node] = v;
                }
            }
        }
    }
}