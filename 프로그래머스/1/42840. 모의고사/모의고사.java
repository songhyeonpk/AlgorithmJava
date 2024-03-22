import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] firstPattern = new int[] {1, 2, 3, 4, 5};
        int[] secondPattern = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] thirdPattern = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int person1 = 0;
        int person2 = 0;
        int person3 = 0;
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == firstPattern[i % 5]) person1++;
            if(answers[i] == secondPattern[i % 8]) person2++;
            if(answers[i] == thirdPattern[i % 10]) person3++;
        }
        
        int max = Math.max(person1, Math.max(person2, person3));
        
        List<Integer> result = new ArrayList<>();
        if(person1 == max) result.add(1);
        if(person2 == max) result.add(2);
        if(person3 == max) result.add(3);
        
        return result.stream().mapToInt(i -> i).toArray();
    }
}