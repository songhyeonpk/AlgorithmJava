import java.util.*;

class Solution {
    static class Village implements Comparable<Village> {
        int to, time;
        
        Village(int to, int time) {
            this.to = to;;
            this.time = time;
        }
        
        @Override
        public int compareTo(Village v) {
            return this.time - v.time;
        }
    }
    
    static List<List<Village>> graph = new ArrayList<>();
    static int[] times;
    
    public int solution(int N, int[][] road, int K) {
        times = new int[N + 1];
        Arrays.fill(times, Integer.MAX_VALUE);
        
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            graph.get(a).add(new Village(b, c));
            graph.get(b).add(new Village(a, c));
        }
        
        delivery(1);
        
        int answer = 0;
        for(int i = 1; i <= N; i++) {
            if(times[i] <= K) {
                answer += 1;
            }
        }
        
        return answer;
    }
    
    private static void delivery(int v) {
        PriorityQueue<Village> pq = new PriorityQueue<>();
        pq.offer(new Village(v, 0));
        times[v] = 0;
        
        while(!pq.isEmpty()) {
            Village now = pq.poll();
            
            for(int i = 0; i < graph.get(now.to).size(); i++) {
                Village next = graph.get(now.to).get(i);
                
                if(times[next.to] > times[now.to] + next.time) {
                    times[next.to] = times[now.to] + next.time;
                    pq.offer(new Village(next.to, times[next.to]));
                }
            }
        }
    }
}