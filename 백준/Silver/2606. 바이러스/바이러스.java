import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int count = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 컴퓨터 수(=정점의 수)
        int vertex = Integer.parseInt(br.readLine());
        // 연결된 선의 수(=간선의 수)
        int edge = Integer.parseInt(br.readLine());
        
        for(int i = 0; i <= vertex; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        visited = new boolean[vertex + 1];
        
        bfs(1);
        br.close();
        System.out.println(count);
    }
    
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        
        while(!queue.isEmpty()) {
            int x = queue.poll();
            
            for(int i = 0; i < graph.get(x).size(); i++) {
                int y = graph.get(x).get(i);
                if(!visited[y]) {
                    queue.offer(y);
                    visited[y] = true;
                    count++;
                }
            }
        }
    }
}