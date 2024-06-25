import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();
        
        List<String> strList = new ArrayList<>();
        strList.add(str);
        
        for(int i = 1; i < str.length(); i++) {
            strList.add(str.substring(i, str.length()));
        }
        
        Collections.sort(strList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for(String s : strList) {
            sb.append(s).append("\n");
        }
        
        System.out.println(sb);
    }
}