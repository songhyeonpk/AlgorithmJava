import java.io.*;
import java.util.*;

public class Main {
    static class Path {
        int to, d;
        
        Path(int to, int d) {
            this.to = to;
            this.d = d;
        }
    }
    static List<List<Path>> pathList = new ArrayList<>();
    static int[] distance;
   
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        
        distance = new int[10001];
        for(int i = 0; i < 10001; i++) {
            pathList.add(new ArrayList<>());
            distance[i] = i;
        }
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            pathList.get(from).add(new Path(to, d));
        }
        
        dijkstra(0, D);
        System.out.println(distance[D]);
    }
    
    private static void dijkstra(int start, int end) {
        if(start > end) {
            return;
        }
        
        if(distance[start + 1] > distance[start] + 1) {
            distance[start + 1] = distance[start] + 1;
        }
        
        for(int i = 0; i < pathList.get(start).size(); i++) {
            Path p = pathList.get(start).get(i);
            if(distance[p.to] > distance[start] + p.d) {
                distance[p.to] = distance[start] + p.d;
            }
        }
        
        dijkstra(start + 1, end);
    }
}