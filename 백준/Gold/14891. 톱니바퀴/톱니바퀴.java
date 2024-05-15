import java.io.*;
import java.util.*;

public class Main {
    static class Sawtooth {
        int top;
        int rt;
        int right;
        int rb;
        int bottom;
        int lb;
        int left;
        int lt;
        
        public Sawtooth(int top, int rt, int right, int rb, int bottom, int lb, int left, int lt) {
            this.top = top;
            this.rt = rt;
            this.right = right;
            this.rb = rb;
            this.bottom = bottom;
            this.lb = lb;
            this.left = left;
            this.lt = lt;
        }
    }
    static Sawtooth[] arr = new Sawtooth[5];
    static int[] rt;
    static class Direction {
        int num;
        int d;
        
        public Direction(int num, int d) {
            this.num = num;
            this.d = d;
        }
    }
    static int[] dx = {1, -1};
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int i = 1; i <= 4; i++) {
            String str = br.readLine();
            
            Sawtooth swt = new Sawtooth(str.charAt(0) - '0', str.charAt(1) - '0', str.charAt(2) - '0',
                      str.charAt(3) - '0', str.charAt(4) - '0', str.charAt(5) - '0',
                      str.charAt(6) - '0', str.charAt(7) - '0');
            
            arr[i] = swt;
        }
        
        int K = Integer.parseInt(br.readLine());
        while(K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            rt = new int[5];
            bfs(num, d);
        }
        br.close();
        
        int sum = 0;
        for(int i = 1; i <= 4; i++) {
            Sawtooth cur = arr[i];
            
            if(cur.top == 1) {
                sum += Math.pow(2, i - 1);
            }
        }
        
        System.out.println(sum);
    }
    
    static void bfs(int num, int d) {
        Queue<Direction> queue = new LinkedList<>();
        queue.offer(new Direction(num, d));
        rt[num] = d;
        
        while(!queue.isEmpty()) {
            Direction now = queue.poll();
            
            for(int i = 0; i < 2; i++) {
                int next = now.num + dx[i];
                
                if(!range_check(next) || rt[next] != 0) {
                    continue;
                }
                
                if((now.num > next && arr[now.num].left == arr[next].right) ||
                   (now.num < next && arr[now.num].right == arr[next].left)) {
                    continue;
                }
                
                rt[next] = -now.d;
                queue.offer(new Direction(next, -now.d));
            }
        }
        
        for(int i = 1; i <= 4; i++) {
            if(rt[i] != 0) {
                rotation(i, rt[i]);
            }
        }
    }
    
    // 회전
    static void rotation(int num, int d) {
        Sawtooth cur = arr[num];
        Sawtooth before = null;
        
        // 시계방향 회전
        if(d == 1) {
            before = new Sawtooth(cur.lt, cur.top, cur.rt, cur.right, cur.rb, cur.bottom, 
                                 cur.lb, cur.left);
        }
        // 반시계방향 회전
        else {
            before = new Sawtooth(cur.rt, cur.right, cur.rb, cur.bottom, cur.lb, cur.left, 
                                 cur.lt, cur.top);
        }
        
        arr[num] = before;
    }
    
    static boolean range_check(int n) {
        return n > 0 && n <= 4;
    }
}