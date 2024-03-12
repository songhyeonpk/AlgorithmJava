import java.util.*;

class Solution {
    static class Room {
        int h, m;
        
        Room(int h, int m) {
            this.h = h;
            this.m = m;
        }
    }
    
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, new Comparator<String[]>() {
           @Override
            public int compare(String[] arr1, String[] arr2) {
                int arr1Hour = Integer.parseInt(arr1[0].split(":")[0]);
                int arr2Hour = Integer.parseInt(arr2[0].split(":")[0]);
                
                if(arr1Hour == arr2Hour) {
                    int arr1Minute = Integer.parseInt(arr1[0].split(":")[1]);
                    int arr2Minute = Integer.parseInt(arr2[0].split(":")[1]);
                    
                    return arr1Minute - arr2Minute;
                }
                
                return arr1Hour - arr2Hour;
            }
        });
        
        Map<Integer, Room> map = new HashMap<>();
        for(int i = 0; i < book_time.length; i++) {
            String ci = book_time[i][0];
            System.out.println(ci);
            int cih = Integer.parseInt(ci.split(":")[0]);
            int cim = Integer.parseInt(ci.split(":")[1]);
            
            String co = book_time[i][1];
            System.out.println(co);
            int coh = Integer.parseInt(co.split(":")[0]);
            int com = Integer.parseInt(co.split(":")[1]);
            
            coh += ((com + 10) / 60);
            com = ((com + 10) % 60);
            
            if(map.size() == 0) {
                map.put(i + 1, new Room(coh, com));
                continue;
            }
            
            boolean isAddRoom = true;
            for(int key : map.keySet()) {
                Room room = map.get(key);
                if(room.h < cih || (room.h == cih && room.m <= cim)) {
                    map.put(key, new Room(coh, com));
                    isAddRoom = false;
                    break;
                }
            }
            
            if(isAddRoom) {
                map.put(i + 1, new Room(coh, com));
            }
        }
        
        return map.size();
    }
}