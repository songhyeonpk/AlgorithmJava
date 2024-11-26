import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        // 신고 당한 결과 (신고 당한 사용자 -> 해당 사용자를 신고한 사용자 목록)
        Map<String, Set<String>> reportedMap = new HashMap<>();
        
        // 신고한 결과 (신고한 사용자 -> 해당 사용자가 신고한 사용자 목록)
        Map<String, Set<String>> reportMap = new HashMap<>();
        
        for(String rp : report) {
            String to = rp.split(" ")[0];
            String from = rp.split(" ")[1];
            
            reportedMap.computeIfAbsent(from, value -> new HashSet<>()).add(to);
            reportMap.computeIfAbsent(to, value -> new HashSet<>()).add(from);
        }
        
        // 정지된 사용자
        List<String> suspendedUsers = new ArrayList<>();
        for(Map.Entry<String, Set<String>> entry : reportedMap.entrySet()) {
            String reportedUser = entry.getKey();
            int count = entry.getValue().size();
            
            if(count >= k) suspendedUsers.add(reportedUser);
        }
        
        // 각 회원 별 신고한 사용자가 정지당한 횟수 구하기
        int[] answer = new int[id_list.length];
        int idx = 0;
        for(String id : id_list) {
            Set<String> reportedByUsers = reportMap.get(id);
            
            int cnt = reportedByUsers != null ? (int) reportedByUsers.stream()
                .filter(suspendedUsers::contains)
                .count() : 0;
            
            answer[idx++] = cnt;
        }
        
        return answer;
    }
}