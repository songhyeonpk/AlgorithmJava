import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        
        char[] arr = num.toCharArray();
        
        // 오름차순 정렬
        Arrays.sort(arr);
        
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for(int i = arr.length - 1; i >= 0; i--) {
            sum += (arr[i] - '0');
            sb.append(arr[i] - '0');
        }
        
        // 모든 원소를 합한 값이 3의 배수가 아니거나 마지막 원소가 0이 아니라면 30의 배수가 아님
        if(sum % 3 != 0 || arr[0] != '0') {
            System.out.println(-1);
            return;
        }
        
        System.out.println(sb);
    }
}