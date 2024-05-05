import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int to, cnt;
        
        Node(int to, int cnt) {
            this.to = to;
            this.cnt = cnt;
        }
    }
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[N + 1][N + 1];
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        br.close();
        
        int result = bfs(A, B);
        System.out.println(result);
    }
    
    private static int bfs(int A, int B) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(A, 0));
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            if(now.to == B) {
                return now.cnt;
            }
            
            for(int i = 0; i < graph.get(now.to).size(); i++) {
                int next = graph.get(now.to).get(i);
                
                if(!visited[now.to][next] && !visited[next][now.to]) {
                    visited[now.to][next] = true;
                    visited[next][now.to] = true;
                    queue.add(new Node(next, now.cnt + 1));
                }
            }
        }
        
        return -1;
    }
}