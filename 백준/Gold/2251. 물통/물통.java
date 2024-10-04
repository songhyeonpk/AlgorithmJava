import java.io.*;
import java.util.*;

public class Main {
    static class Water {
        int a, b, c;
        
        Water(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    static int totalA, totalB, totalC;
    static boolean[][][] check;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        totalA = Integer.parseInt(st.nextToken());
        totalB = Integer.parseInt(st.nextToken());
        totalC = Integer.parseInt(st.nextToken());
        check = new boolean[totalA + 1][totalB + 1][totalC + 1];
        
        List<Integer> result = bfs();
        Collections.sort(result);    // 오름차순으로 정렬
        
        StringBuilder sb = new StringBuilder();
        for(int num : result) {
            sb.append(num);
            sb.append(" ");
        }
        
        System.out.println(sb);
    }
    
    public static List<Integer> bfs() {
        Queue<Water> queue = new LinkedList<>();
        queue.add(new Water(0, 0, totalC));    // 초기 물 세팅
        
        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            Water now = queue.poll();
            int a = now.a;
            int b = now.b;
            int c = now.c;
            
            if(check[a][b][c]) continue;    // 이미 체크한 물의 양일 경우 continue
            
            check[a][b][c] = true;
            if(a == 0) result.add(c);    // a 물 양이 0이라면 c 물의 양을 리스트에 추가
            
            // 물 옮기기
            transferWater(queue, a, b, c, totalB, "aToB");    // a -> b
            transferWater(queue, a, c, b, totalC, "aToC");    // a -> c
            transferWater(queue, b, a, c, totalA, "bToA");    // b -> a
            transferWater(queue, b, c, a, totalC, "bToC");    // b -> c
            transferWater(queue, c, a, b, totalA, "cToA");    // c -> a
            transferWater(queue, c, b, a, totalB, "cToB");    // c -> b
        }
        
        return result;
    }
    
    public static void transferWater(Queue<Water> queue, int from, int to, int spare, int limit, String action) {
        int newFrom = from;
        int newTo = to;
        
        if(from + to >= limit) {    // 물이 넘칠 경우
            newFrom = from - (limit - to);
            newTo = limit;
        } else {    // 물이 넘치지 않을 경우
            newFrom = 0;
            newTo = from + to;
        }
        
        switch(action) {
            case "aToB":    // a 물을 b로 이동
                queue.add(new Water(newFrom, newTo, spare));
                break;
            case "aToC":    // a 물을 c로 이동
                queue.add(new Water(newFrom, spare, newTo));
                break;
            case "bToA":    // b 물을 a로 이동
                queue.add(new Water(newTo, newFrom, spare));
                break;
            case "bToC":    // b 물을 c로 이동
                queue.add(new Water(spare, newFrom, newTo));
                break;
            case "cToA":    // c 물을 a로 이동
                queue.add(new Water(newTo, spare, newFrom));
                break;
            case "cToB":    // c 물을 b로 이동
                queue.add(new Water(spare, newTo, newFrom));
                break;
        }
    }
}