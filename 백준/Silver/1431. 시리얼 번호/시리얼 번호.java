import java.io.*;
import java.util.*;

public class Main {
    static class SerialNumber implements Comparable<SerialNumber>{
        String serial;
        int len, sum;
        
        SerialNumber(String serial, int len, int sum) {
            this.serial = serial;
            this.len = len;
            this.sum = sum;
        }
        
        @Override
        public int compareTo(SerialNumber sn) {
            if(this.len == sn.len) {
                // 시리얼 번호를 사전순으로 정렬
                if(this.sum == sn.sum) return this.serial.compareTo(sn.serial);
                
                // 자릿수 합을 기준으로 오름차순 정렬
                return this.sum - sn.sum;
            }
            
            // 길이를 기준으로 오름차순 정렬
            return this.len - sn.len;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        List<SerialNumber> snList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            String serialNumber = br.readLine();
            int len = serialNumber.length();
            int sum = 0;
            
            for(int j = 0; j < len; j++) {
                char ch = serialNumber.charAt(j);
                
                // 숫자일 경우 자릿수 합 구하기
                if(Character.isDigit(ch)) {
                    sum += (ch - '0');
                }
            }
            
            snList.add(new SerialNumber(serialNumber, len, sum));
        }
        br.close();
        
        // 정렬
        Collections.sort(snList);
        
        StringBuilder sb = new StringBuilder();
        for(SerialNumber sn : snList) {
            sb.append(sn.serial);
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
}