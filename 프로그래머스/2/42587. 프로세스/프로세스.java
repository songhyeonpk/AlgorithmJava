import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            queue.offer(new int[] {i, priorities[i]});
        }
        
        int[] result = new int[priorities.length];
        int sequence = 1;
        while(!queue.isEmpty()) {
            int[] nums = queue.poll();
            
            boolean check = false;
            int size = queue.size();
            while(size-- > 0) {
                int[] head = queue.poll();
                if(head[1] > nums[1]) {
                    check = true;
                }
                queue.offer(new int[] {head[0], head[1]});
            }
            
            if(!check) {
                result[nums[0]] = sequence++;
                continue;
            }
            
            queue.offer(nums);
        }
        
        return result[location];
    }
}