import java.util.*;
import java.lang.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        int extIndex = 0;
        if(ext.equals("date")) {
            extIndex = 1;
        } else if (ext.equals("maximum")) {
            extIndex = 2;
        } else if (ext.equals("remain")) {
            extIndex = 3;
        }
        
        List<int[]> dataList = new ArrayList<>();
        for(int i = 0; i < data.length; i++) {
            if(data[i][extIndex] <= val_ext) {
                dataList.add(new int[]{data[i][0], data[i][1], data[i][2], data[i][3]});
            }
        }
        
        Collections.sort(dataList, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                int sortByIndex = 0;
                if(sort_by.equals("date")) {
                    sortByIndex = 1;
                } else if (sort_by.equals("maximum")) {
                    sortByIndex = 2;
                } else if (sort_by.equals("remain")) {
                    sortByIndex = 3;
                }
                
                return i1[sortByIndex] - i2[sortByIndex];
            }
        });
        
        int[][] answer = new int[dataList.size()][4];
        for(int i = 0; i < dataList.size(); i++) {
            int[] answerArr = dataList.get(i);
            
            for(int j = 0; j < 4; j++) {
                answer[i][j] = answerArr[j];
            }
        }
        
        return answer;
    }
}