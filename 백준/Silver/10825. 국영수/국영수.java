import java.io.*;
import java.util.*;

public class Main {
    static class Student implements Comparable<Student> {
        String name;
        int korean, english, math;
        
        Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }
        
        @Override
        public int compareTo(Student s) {
            if(s.korean == this.korean) {
                if(s.english == this.english) {
                    if(s.math == this.math) {
                        return this.name.compareTo(s.name);
                    }
                    
                    return s.math - this.math;
                }
                
                return this.english - s.english;
            }
            
            return s.korean - this.korean;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        List<Student> students = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            
            students.add(new Student(name, korean, english, math));
        }
        br.close();
        
        Collections.sort(students);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            Student student = students.get(i);
            
            sb.append(student.name).append("\n");
        }
        
        System.out.println(sb);
    }
}