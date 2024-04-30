import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static List<List<Integer>> graph;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            parent = new int[N + 1];
            graph = new ArrayList<>();
            for(int j = 0; j < N + 1; j++) {
                parent[j] = j;
                graph.add(new ArrayList<>());
            }
            
            for(int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                graph.get(from).add(to);
                graph.get(to).add(from);
            }
            
            int result = 0;
            for(int j = 1; j < N + 1; j++) {
                List<Integer> list = graph.get(j);
                
                for(int k = 0; k < list.size(); k++) {
                    if(find(j) != find(list.get(k))) {
                        union(j, list.get(k));
                        result += 1;
                    }
                }
            }
            
            sb.append(result).append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
    
    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        
        if(aParent < bParent) {
            parent[bParent] = parent[aParent];
        } else {
            parent[aParent] = parent[bParent];
        }
    }
    
    private static int find(int i) {
        if(parent[i] == i) {
            return i;
        }
        
        return parent[i] = find(parent[i]);
    }
}