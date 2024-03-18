import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++) {
            queue.offer(new int[] {progresses[i], speeds[i]});
        }
        
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] nums = queue.poll();
                queue.offer(new int[] {nums[0] + nums[1], nums[1]});
            }
            
            int cnt = 0;
            while(!queue.isEmpty() && queue.peek()[0] > 99) {
                queue.poll();
                cnt++;
            }
            
            if(cnt > 0) {
                list.add(cnt);
            }
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}