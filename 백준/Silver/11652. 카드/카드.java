import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Map<Long, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        br.close();
        
        List<Map.Entry<Long, Integer>> cards = new ArrayList<>(map.entrySet());
        Collections.sort(cards, new Comparator<Map.Entry<Long, Integer>>(){
           @Override
           public int compare(Map.Entry<Long, Integer> e1, Map.Entry<Long, Integer> e2) {
               // 개수가 같다면 숫자를 기준으로 오름차순 정렬
               if(e1.getValue().equals(e2.getValue())) {
                   return e1.getKey().compareTo(e2.getKey());
               }
               
               // 개수를 기준으로 내림차순 정렬
               return e2.getValue() - e1.getValue();
           }
        });
        
        System.out.println(cards.get(0).getKey());
    }
}