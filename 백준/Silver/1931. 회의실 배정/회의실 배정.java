import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int count = 0;
        
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        br.close();
        
        Arrays.sort(arr, new Comparator<int[]>(){
           @Override
            public int compare(int[] a1, int[] a2) {
                if(a1[1] == a2[1]) {
                    return a1[0] - a2[0];
                }
                return a1[1] - a2[1];
            }
        });
        
        int prevEndTime = 0;
        for(int i = 0; i < N; i++) {
            int startTime = arr[i][0];
            if(prevEndTime <= startTime) {
                count++;
                prevEndTime = arr[i][1];
            }
        }
        
        System.out.println(count);
    }
}