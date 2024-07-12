import java.io.*;
import java.util.*;

public class Main {
    static class Student {
        // 추천받은 횟수, 게시된 순서(사진)
        int recommendationCount, postingOrder;
        
        Student(int recommendationCount, int postingOrder) {
            this.recommendationCount = recommendationCount;
            this.postingOrder = postingOrder;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        Student[] students = new Student[101];
        for(int i = 1; i <= 100; i++) {
            students[i] = new Student(0, 0);
        }
        
        List<Integer> postingList = new ArrayList<>();
        int postingOrder = 1;
        while(K-- > 0) {
            int studentNum = Integer.parseInt(st.nextToken());
            
            // 추천받은 학생의 사진이 이미 게시되어 있을 경우
            // 해당 학생의 추천횟수만 증가
            if(postingList.contains(studentNum)) {
                students[studentNum].recommendationCount += 1;
                continue;
            }
            
            // 사진이 모두 게시되어 비어있는 사진틀이 없을 경우
            if(postingList.size() >= N) {
                
                // 게시되어 있는 사진들의 학생 추천 횟수를 기준으로 오름차순 정렬
                // 만약 가장 적은 추천 횟수를 가진 학생이 2명 이상이면 사진이 게시된 순서를 기준으로 오름차순 정렬
                Collections.sort(postingList, new Comparator<Integer>(){
                    @Override
                    public int compare(Integer sn1, Integer sn2) {
                        int sn1RcdCount = students[sn1].recommendationCount;
                        int sn2RcdCount = students[sn2].recommendationCount;
                        if(sn1RcdCount == sn2RcdCount) {
                            return students[sn1].postingOrder - students[sn2].postingOrder;
                        }
                        
                        return sn1RcdCount - sn2RcdCount;
                    }
                });
                
                // 정렬 후 삭제될 사진의 학생 번호
                int removeNum = postingList.get(0);
                
                // 해당 학생의 추천 횟수, 게시된 순서 정보 초기화
                // 게시된 사진에서 삭제
                students[removeNum] = new Student(0, 0);
                postingList.remove(0);
            }
            
            // 새로 추천 받은 학생 정보 업데이트 및 사진 게시
            students[studentNum] = new Student(students[studentNum].recommendationCount + 1, postingOrder++);
            postingList.add(studentNum);
        }
        
        Collections.sort(postingList);
        
        StringBuilder sb = new StringBuilder();
        for(int num : postingList) {
            sb.append(num);
            sb.append(" ");
        }
        
        System.out.println(sb);
    }
}