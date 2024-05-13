import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int cost;
        
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
    static ArrayList<ArrayList<Node>> edge = new ArrayList<>();
    static int[] distance;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        for(int i = 0; i <= N; i++) {
            edge.add(new ArrayList<>());
        }
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            
            edge.get(from).add(new Node(to, 1));
        }
        
        dijkstra(X);
        
        ArrayList<Integer> city = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(distance[i] == K) {
                city.add(i);
            }
        }
        
        if(city.isEmpty()) {
            sb.append(-1);
        } else {
            Collections.sort(city);
            for(int i = 0; i < city.size(); i++) {
                sb.append(city.get(i)).append("\n");
            }
        }
        
        br.close();
        System.out.println(sb);  
    }
    
    static void dijkstra(int startV) {
        distance[startV] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startV, 0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int nowV = now.to;
            
            for(Node next : edge.get(nowV)) {
                if(distance[next.to] > distance[nowV] + next.cost) {
                    distance[next.to] = distance[nowV] + next.cost;
                    pq.offer(new Node(next.to, distance[next.to]));
                }
            }
        }
    }
}