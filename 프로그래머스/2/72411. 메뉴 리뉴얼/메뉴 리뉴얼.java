import java.util.*;

class Solution {
    static Map<String, Integer> courseMenuMap = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        
        // 주문내역에 따라 코스 후보 메뉴 등록
        for(String order : orders) {
            for(int i = 2; i <= order.length(); i++) {
                makeCourseMenu(0, 0, i, "", order);
            }
        }
        
        // 메뉴의 수에 따라 코스 메뉴 선정
        List<String> result = new ArrayList<>();
        for(int num : course) {
            int maxNum = 0;
            List<String> menuList = new ArrayList<>();
            
            for(Map.Entry<String, Integer> entry : courseMenuMap.entrySet()) {
                String courseName = entry.getKey();
                int orderCnt = entry.getValue();
                
                // 메뉴의 수 다른 경우, 주문횟수가 1번 미만인 경우는 후보에서 제외
                if(courseName.length() != num || orderCnt <= 1) {
                    continue;
                }

                if(maxNum < orderCnt) {
                    maxNum = orderCnt;
                    menuList.clear();
                    menuList.add(courseName);
                    continue;
                }
                
                if(maxNum == orderCnt) {
                    menuList.add(courseName);
                }
            }
            
            result.addAll(menuList);
        }
        
        // 사전순 오름차순 정렬
        Collections.sort(result, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        
        String[] answer = new String[result.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    // 코스 메뉴 만드는 로직
    private static void makeCourseMenu(int start, int depth, int k, String menu, String order) {
        if(depth == k) {
            char[] menuArr = menu.toCharArray();
            
            // 중복 메뉴 구성을 구별하기 위해 사전순으로 정렬 후 후보에 저장
            Arrays.sort(menuArr);
            
            String courseMenu = "";
            for(char ch : menuArr) {
                courseMenu += ch;
            }
            
            courseMenuMap.put(courseMenu, courseMenuMap.getOrDefault(courseMenu, 0) + 1);
            return;
        }
        
        for(int i = start; i < order.length(); i++) {
            makeCourseMenu(i + 1, depth + 1, k, menu + order.charAt(i), order);
        }
    }
}