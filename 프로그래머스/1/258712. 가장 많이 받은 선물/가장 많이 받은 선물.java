import java.util.*;

class Solution {
    public static int[][] giftArr;
    
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < friends.length; i++) {
            indexMap.put(friends[i], i + 1);
        }
        
        giftArr = new int[friends.length + 1][friends.length + 1];
        for(String str : gifts) {
            int givenerIndex = indexMap.get(str.split(" ")[0]);
            int receiverIndex = indexMap.get(str.split(" ")[1]);
            
            giftArr[givenerIndex][receiverIndex]++;
        }
        
        int answer = 0;
        for(int i = 1; i < friends.length + 1; i++) {
            int result = 0;
            int firstGiftIndex = giftIndex(i);
            
            for(int j = 1; j < friends.length + 1; j++) {
                if(i == j) {
                    continue;
                }
                
                int givenCount = giftArr[i][j];
                int receiveCount = giftArr[j][i];
                if(givenCount > receiveCount) {
                    result++;
                }
                
                if((givenCount == 0 && receiveCount == 0) || givenCount == receiveCount) {
                    if(firstGiftIndex > giftIndex(j)) {
                        result++;
                    }
                }
            }
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
    
    public static int giftIndex(int friend) {
        int givenCount = 0;
        int receiveCount = 0;
        for(int i = 1; i < giftArr.length; i++) {
            if(friend == i) {
                continue;
            }
            givenCount += giftArr[friend][i];
            receiveCount += giftArr[i][friend];
        }
        
        return givenCount - receiveCount;
    }
}