import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class Seat implements Comparable<Seat>{
        Point point;
        int favoriteCnt, emptyCnt;
        
        Seat(Point point, int favoriteCnt, int emptyCnt) {
            this.point = point;
            this.favoriteCnt = favoriteCnt;
            this.emptyCnt = emptyCnt;
        }
        
        @Override
        public int compareTo(Seat s) {
            if(this.favoriteCnt == s.favoriteCnt) {
                if(this.emptyCnt == s.emptyCnt) {
                    if(this.point.x == s.point.x) {
                        // 자리의 열을 기준으로 오름차순 정렬
                        return this.point.y - s.point.y;
                    }
                    
                    // 자리의 행을 기준으로 오름차순 정렬
                    return this.point.x - s.point.x;
                }
                
                // 주변에 비어있는 자리 개수를 기준으로 내림차순 정렬
                return s.emptyCnt - this.emptyCnt;
            }
            
            // 주변에 좋아하는 친구가 앉은 자리 개수를 기준으로 내림차순으로 정렬
            return s.favoriteCnt - this.favoriteCnt;
        }
    }
    
    static class Student {
        int num;
        List<Integer> favoriteFriends = new ArrayList<>();
        Seat seat;
        
        Student(int num) {
            this.num = num;
        }
        
        // 좋아하는 친구 추가
        void addFriend(int n) {
            this.favoriteFriends.add(n);
        }
        
        // 자리 배치
        void placementSeat(Seat seat) {
            this.seat = seat;
        }
    }
    
    static int N;
    static int[][] classroom;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N];
        
        List<Student> students = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            
            int num = Integer.parseInt(st.nextToken());
            Student student = new Student(num);
            for(int j = 0; j < 4; j++) {
                int friend = Integer.parseInt(st.nextToken());
                student.addFriend(friend);
            }
            
            students.add(student);
        }
        br.close();
        
        for(Student student : students) {
            Seat result = allocationSeat(student);
            student.placementSeat(result);
        }
        
        // 학생별 만족 점수 구하기
        int score = 0;
        for(Student student : students) {
            score += getScore(student.seat, student.favoriteFriends);
        }
        
        System.out.println(score);
    }
    
    private static Seat allocationSeat(Student student) {
        List<Seat> candidates = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(classroom[i][j] == 0) {
                    candidates.add(findSeat(i, j, student.favoriteFriends));
                }
            }
        }
        
        if(candidates.size() > 1) {
            Collections.sort(candidates);
        }
        
        Seat result = candidates.get(0);
        classroom[result.point.x][result.point.y] = student.num;
        
        return result;
    }
    
    private static Seat findSeat(int x, int y, List<Integer> favoriteFriends) {
        Seat seat = new Seat(new Point(x, y), 0, 0);
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(!rangeCheck(nx, ny)) {
                continue;
            }
            
            // 인접한 자리에 좋아하는 친구가 있는 경우
            if(favoriteFriends.contains(classroom[nx][ny])) {
                seat.favoriteCnt += 1;
            }
            
            // 인접한 자리가 비어있는 경우
            if(classroom[nx][ny] == 0) {
                seat.emptyCnt += 1;
            }
        }
        
        return seat;
    }
    
    private static int getScore(Seat seat, List<Integer> favoriteFriends) {
        int cnt = 0;
        for(int i = 0; i < 4; i++) {
            int nx = seat.point.x + dx[i];
            int ny = seat.point.y + dy[i];
            
            if(!rangeCheck(nx, ny)) {
                continue;
            }
            
            if(favoriteFriends.contains(classroom[nx][ny])) {
                cnt += 1;
            }
        }
        
        // 0 : 0
        // 1 : 1
        // 2 : 10
        // 3 : 100
        // 4 : 1000
        return (int)Math.pow(10, cnt - 1);
    }
    
    private static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}