import java.io.*;
import java.util.*;

public class Main {
    static class Load implements Comparable<Load> {
        int to, time;
        
        Load(int to, int time) {
            this.to = to;
            this.time = time;
        }
        
        @Override
        public int compareTo(Load l) {
            return this.time - l.time;
        }
    }
    
    static int N;
    static List<List<Load>> graph = new ArrayList<>();
    static int[] dist;
    static int maxTime = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            
            graph.get(a).add(new Load(b, time));
        }
        br.close();
        
        for(int i = 1; i <= N; i++) {
            int goTime = dijkstra(i, X);
            int comeTime = dijkstra(X, i);
            maxTime = Math.max(maxTime, goTime + comeTime);
        }
        
        System.out.println(maxTime);
    }
    
    static int dijkstra(int start, int end) {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Load> pq = new PriorityQueue<>();
        pq.offer(new Load(start, 0));
        
        while(!pq.isEmpty()) {
            Load now = pq.poll();
   
            for(Load next : graph.get(now.to)) {
                if(dist[next.to] > dist[now.to] + next.time) {
                    dist[next.to] = dist[now.to] + next.time;
                    pq.offer(new Load(next.to, dist[next.to]));
                }
            }  
        }
        
        return dist[end];
    }
}