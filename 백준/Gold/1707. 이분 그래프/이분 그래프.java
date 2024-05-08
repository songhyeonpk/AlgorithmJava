import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    static int[] color;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            color = new int[V + 1];
            
            graph = new ArrayList<>();
            for(int j = 0; j <= V; j++) {
                graph.add(new ArrayList<>());
            }
            
            for(int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                graph.get(from).add(to);
                graph.get(to).add(from);
            }
            
            boolean result = true;
            for(int j = 1; j <= V; j++) {
                if(color[j] == 0) {
                    if(!bfs(j)) {
                        result = false;
                        break;
                    }
                }
            }
            
            sb.append(result ? "YES" : "NO").append("\n");
            
        }
        br.close();
        
        System.out.println(sb);
    }
    
    private static boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        color[v] = 1;
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            int nextColor = color[now] % 2 + 1;
            
            for(int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i);
                
                if(color[next] != 0 && color[next] != nextColor) {
                    return false;
                } 
                
                if(color[next] == 0) {
                    color[next] = nextColor;
                    queue.offer(next);
                }
            }
        }
        
        return true;
    }
}