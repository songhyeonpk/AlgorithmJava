import java.io.*;
import java.util.*;

public class Main {
    static class Book {
        String name;
        int cnt;
        
        Book(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        br.close();
        
        List<Book> books = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            books.add(new Book(entry.getKey(), entry.getValue()));
        }
        
        Collections.sort(books, new Comparator<Book>(){
            @Override
            public int compare(Book b1, Book b2) {
                if(b1.cnt == b2.cnt) {
                    return b1.name.compareTo(b2.name);
                }
                
                return b2.cnt - b1.cnt;
            }
        });
        
        System.out.println(books.get(0).name);
    }
}