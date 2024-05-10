import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static int size;
    static boolean[][] visited;
    static int[][] graph;
    static int[] dirX = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dirY = {-1, -2, -2, -1, 1, 2, 2, 1};
    
    static class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < T; i++) {
            size = Integer.parseInt(br.readLine());
            visited = new boolean[size][size];
            graph = new int[size][size];
            
            st = new StringTokenizer(br.readLine(), " ");
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine(), " ");
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            
            bfs(startX, startY);
            sb.append(graph[endX][endY]).append("\n");
        }
        br.close();
        System.out.println(sb);
    }
    
    static void bfs(int startX, int startY) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY));
        visited[startX][startY] = true;
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int nowX = node.x;
            int nowY = node.y;
            
            for(int i = 0; i < 8; i++) {
                int nextX = nowX + dirX[i];
                int nextY = nowY + dirY[i];
                
                if(range_check(nextX, nextY) && !visited[nextX][nextY]) {
                    queue.offer(new Node(nextX, nextY));
                    visited[nextX][nextY] = true;
                    graph[nextX][nextY] = graph[nowX][nowY] + 1;
                }
            }
        }
    }
    
    static boolean range_check(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}