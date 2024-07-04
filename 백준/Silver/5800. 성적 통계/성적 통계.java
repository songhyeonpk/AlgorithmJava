import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        int classNumber = 1;
        while(N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            
            List<Integer> scores = new ArrayList<>();
            for(int i = 0; i < K; i++) {
                scores.add(Integer.parseInt(st.nextToken()));
            }
                
            // 성적으로 내림차순 정렬
            Collections.sort(scores, Collections.reverseOrder());
            
            int maxScore = scores.get(0);
            int minScore = scores.get(K - 1);
            int maxScoreDiff = Integer.MIN_VALUE;
            for(int i = 0; i < K - 1; i++) {
                maxScoreDiff = Math.max(maxScoreDiff, scores.get(i) - scores.get(i + 1));
            }
            
            sb.append("Class " + classNumber++).append("\n");
            sb.append("Max " + maxScore + ", ");
            sb.append("Min " + minScore + ", ");
            sb.append("Largest gap " + maxScoreDiff).append("\n");
        }
        br.close();
        
        System.out.println(sb);
    }
}