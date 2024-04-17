import java.util.*;

class Solution {
    public static Map<Integer, Map<Character, Integer>> resultMap = new HashMap<>();
    public static int[] points = {0, 3, 2, 1, 0, 1, 2, 3};
    
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> surveyMap = new HashMap<>();
        surveyMap.put("RT", 1);
        surveyMap.put("TR", 1);
        surveyMap.put("FC", 2);
        surveyMap.put("CF", 2);
        surveyMap.put("MJ", 3);
        surveyMap.put("JM", 3);
        surveyMap.put("AN", 4);
        surveyMap.put("NA", 4);
        
        char[][] typeArr = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        int cnt = 1;
        for(int i = 0; i < 4; i++) {
            Map<Character, Integer> type = new HashMap<>();
            type.put(typeArr[i][0], 0);
            type.put(typeArr[i][1], 0);
            
            resultMap.put(cnt++, type);
        }
        
        for(int i = 0; i < survey.length; i++) {
            if(choices[i] == 4) {
                continue;
            }
            
            point(surveyMap.get(survey[i]), survey[i], choices[i]);
        }
        
        String answer = "";
        for(int i = 0; i < 4; i ++) {
            Map<Character, Integer> type = resultMap.get(i + 1);
            char aType = typeArr[i][0];
            char bType = typeArr[i][1];
            int aTypePoint = type.get(aType);
            int bTypePoint = type.get(bType);
            
            char resultType = aType;
            if(aTypePoint < bTypePoint ||
               (aTypePoint == bTypePoint && bType - aType < 0)) {
                resultType = bType;
            }
            
            answer += resultType;
        }
        
        return answer;
    }
    
    private static void point(int typeNumber, String survey, int choice) {
        char type = survey.charAt(0);
        if(choice > 4) {
            type = survey.charAt(1); 
        }
        
        Map<Character, Integer> typeMap = resultMap.get(typeNumber);
        typeMap.put(type, typeMap.get(type) + points[choice]);
        
        resultMap.put(typeNumber, typeMap);
    }
}