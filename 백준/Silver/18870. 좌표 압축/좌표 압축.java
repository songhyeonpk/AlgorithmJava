import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] arrCopy = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            arrCopy[i] = num;
        }
        br.close();
        
        Arrays.sort(arrCopy);
        
        int rank = 0;
        for(int i : arrCopy) {
            if(!map.containsKey(i)) {
                map.put(i, rank);
                rank++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int key : arr) {
            sb.append(map.get(key)).append(" ");
        }
       
        System.out.println(sb);
    }
}