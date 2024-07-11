import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        
        Map<Integer, Stack<Integer>> guitar = new HashMap<>();
        for(int i = 1; i <= 6; i++) {
            guitar.put(i, new Stack<>());
        }
        
        int cnt = 0;
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());
            
            // 현재 기타줄에서 누르고 있는 프렛 목록 
            Stack<Integer> frets = guitar.get(line);
            
            // 누르고 있는 프렛이 존재하지 않거나 누르고 있는 프렛보다 눌려야 될 프렛 번호가 더 큰 경우
            // 손가락으로 한번 누름
            if(frets.isEmpty() || frets.peek() < fret) {
                cnt += 1;
                frets.push(fret);
                guitar.put(line, frets);
                continue;
            }
            
            // 누르고 있는 프렛이 눌려야 될 프렛보다 큰 경우
            // 눌러야 될 프렛과 같거나 작을 때까지 손가락을 뗌
            while(!frets.isEmpty() && frets.peek() > fret) {
                cnt += 1;
                frets.pop();
            }
            
            // 누르고 있는 프렛이 없거나 누르고 있는 프렛과 눌려야 될 프렛 번호가 다른 경우
            // 손가락으로 한번 누름
            if(frets.isEmpty() || frets.peek() != fret) {
                cnt += 1;
                frets.push(fret);
                guitar.put(line, frets);
            }
        }
        br.close();
        
        System.out.println(cnt);
    }
}