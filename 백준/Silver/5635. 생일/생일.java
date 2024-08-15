import java.io.*;
import java.util.*;

public class Main {
    static class Birthday {
        String name;
        int day, month, year;
        
        Birthday(String name, int day, int month, int year) {
            this.name = name;
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        List<Birthday> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            
            list.add(new Birthday(name, day, month, year));
        }
        br.close();
        
        Collections.sort(list, new Comparator<Birthday>() {
            @Override
            public int compare(Birthday b1, Birthday b2) {
                if(b1.year == b2.year) {
                    if(b1.month == b2.month) {
                        // 태어난 일을 기준으로 오름차순 정렬
                        return b1.day - b2.day;
                    }
                    
                    // 태어난 월을 기준으로 오름차순 정렬
                    return b1.month - b2.month;
                }
                
                // 태어난 년도를 기준으로 오름차순 정렬
                return b1.year - b2.year;
            }
        });
        
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(list.size() - 1).name);
        sb.append("\n");
        sb.append(list.get(0).name);
        
        System.out.println(sb);
    }
}