import java.util.*;

class Solution {
    static class Files {
        int rank;
        String head, number, tail;
        
        Files(int rank, String head, String number, String tail) {
            this.rank = rank;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }
    
    public String[] solution(String[] files) {
        List<Files> fileList = new ArrayList<>();
        for(int i = 0; i < files.length; i++) {
            String file = files[i];
            
            int headIdx = 0;
            int numberIdx = file.length();
            int tailIdx = file.length();
            for(int j = 0; j < file.length(); j++) {
                char ch = file.charAt(j);
                if(headIdx == 0 && Character.isDigit(ch)) {
                    headIdx = j;
                    continue;
                }
                
                if(headIdx != 0 && !Character.isDigit(ch)) {
                    numberIdx = j;
                    break;
                }
            }
            
            String head = file.substring(0, headIdx);
            String number = file.substring(headIdx, numberIdx);
            String tail = file.substring(numberIdx, tailIdx);
            fileList.add(new Files(i, head, number, tail));
        }
        
        Collections.sort(fileList, new Comparator<Files>(){
            @Override
            public int compare(Files f1, Files f2) {
                String f1Head = f1.head.toLowerCase();
                String f2Head = f2.head.toLowerCase();
                if(f1Head.equals(f2Head)) {
                    int f1Num = Integer.parseInt(f1.number);
                    int f2Num = Integer.parseInt(f2.number);
                    if(f1Num == f2Num) {
                        return f1.rank - f2.rank;
                    }
                    
                    return f1Num - f2Num;
                }
                
                return f1Head.compareTo(f2Head);
            }
        });
        
        String[] answer = new String[fileList.size()];
        for(int i = 0; i < fileList.size(); i++) {
            Files f = fileList.get(i);
            answer[i] = f.head + f.number + f.tail;
        }
        
        return answer;
    }
}