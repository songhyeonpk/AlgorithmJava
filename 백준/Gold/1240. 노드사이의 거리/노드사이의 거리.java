import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int v, dist;
        
        Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }
    
    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            graph.get(from).add(new Node(to, d));
            graph.get(to).add(new Node(from, d));
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            visited = new boolean[N + 1];
            
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            
            sb.append(bfs(from, to)).append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
    
    private static int bfs(int start, int end) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visited[start] = true;
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            if(now.v == end) {
                return now.dist;
            }
            
            for(int i = 0; i < graph.get(now.v).size(); i++) {
                Node next = graph.get(now.v).get(i);
                
                if(!visited[next.v]) {
                    visited[next.v] = true;
                    queue.add(new Node(next.v, now.dist + next.dist));
                }
            }
        }
        
        return -1;
    }
}