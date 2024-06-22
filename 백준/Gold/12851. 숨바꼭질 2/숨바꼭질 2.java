import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, time;
        
        Point(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
    
    static boolean[] visited = new boolean[100001];
    static List<Point> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] answer = bfs(N, K);
        
        StringBuilder sb = new StringBuilder();
        sb.append(answer[0]).append("\n").append(answer[1]);
        
        System.out.println(sb);
    }
    
    private static int[] bfs(int n, int k) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(n, 0));
        visited[n] = true;
        
        int minTime = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            int x = now.x;
            int time = now.time;
            if(x == k) {
                minTime = Math.min(minTime, time);
                list.add(now);
                continue;
            }
            
            visited[x] = true;
            if(rangeCheck(x - 1) && !visited[x - 1]) {
                queue.add(new Point(x - 1, time + 1));
            }
            
            if(rangeCheck(x + 1) && !visited[x + 1]) {
                queue.add(new Point(x + 1, time + 1));
            }
            
            if(rangeCheck(x * 2) && !visited[x * 2]) {
                queue.add(new Point(x * 2, time + 1));
            }
        }
        
        int cnt = 0;
        for(Point point : list) {
            if(minTime == point.time) {
                cnt += 1;
            }
        }
        
        return new int[] {minTime, cnt};
    }
    
    private static boolean rangeCheck(int n) {
        return n >= 0 && n <= 100000;
    }
}