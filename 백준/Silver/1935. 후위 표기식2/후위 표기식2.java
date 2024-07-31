import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String rpn = br.readLine();
        
        // 알파벳별 수를 저장하는 배열
        double[] alphabets = new double[26];
        for(int i = 0; i < N; i++) {
            alphabets[i] = Double.parseDouble(br.readLine());
        }
        br.close();
        
        Stack<Double> stack = new Stack<>();
        for(int i = 0; i < rpn.length(); i++) {
            char ch = rpn.charAt(i);
            
            // 알파벳 대문자일 경우
            // 알바펫 배열에서 해당 알파벳의 수를 찾아 stack에 push
            if(ch >= 'A' && ch <= 'Z') {
                stack.push(alphabets[ch - 'A']);
                continue;
            }
            
            double A = stack.pop();
            double B = stack.pop();
            double result = A + B;
            if(ch == '-') {
                result = B - A;
            } else if (ch == '*') {
                result = B * A;
            } else if (ch == '/') {
                result = B / A;
            }
            
            stack.push(result);
        }
        
        System.out.println(String.format("%.2f", stack.pop()));
    }
}