import java.util.*;

class Solution {    
    public int[] solution(int[] fees, String[] records) {
        int basicTime = fees[0];
        int basicFare = fees[1];
        int unitTime = fees[2];
        int unitFare = fees[3];
        
        Map<String, int[]> carIn = new HashMap<>();
        Map<String, Integer> parkingTime = new HashMap<>();
        for(String record : records) {
            String time = record.split(" ")[0];
            String number = record.split(" ")[1];
            String inOut = record.split(" ")[2];
            
            // System.out.println("number : " + number + ", inout : " + inOut);
            
            int hour = Integer.parseInt(time.split(":")[0]);
            int minute = Integer.parseInt(time.split(":")[1]);
            
            // 입차인 경우
            if(inOut.equals("IN")) {
                carIn.put(number, new int[] {hour, minute});
                continue;
            }
            
            // 출차인 경우
            int[] in = carIn.get(number);
            
            // System.out.println("차량번호 <" + number + "> 출차 기록");
            
            int totalTime = convertTimeToMinute(in[0], hour, in[1], minute);
            // System.out.println("주차한 총 시간(분) : " + totalTime);
            
            parkingTime.put(number, parkingTime.getOrDefault(number, 0) + totalTime);
            carIn.remove(number);
        }
        
        // 출차기록이 없는 경우
        if(carIn.size() != 0) {
            // System.out.println("================= 출차 기록이 없는 차량 =================");
            
            for(Map.Entry<String, int[]> entry : carIn.entrySet()) {
                String number = entry.getKey();
                int[] in = entry.getValue();
                
                // System.out.println("차량번호 <" + number + "> 출차 기록");
                
                int totalTime = convertTimeToMinute(in[0], 23, in[1], 59);
                // System.out.println("주차한 시간(분) : " + totalTime);
                
                parkingTime.put(number, parkingTime.getOrDefault(number, 0) + totalTime);
            }
        }
        
        // 요금 계산
        int[][] result = new int[parkingTime.size()][2];
        int index = 0;
        for(Map.Entry<String, Integer> entry : parkingTime.entrySet()) {
            String number = entry.getKey();
            int totalTime = entry.getValue();
            int totalFare = basicFare;
            if(totalTime > basicTime) {
                totalFare += (int)Math.ceil((double)(totalTime - basicTime) / unitTime) * unitFare;
            }
            // System.out.println("차량번호 <" + number + "> 총 주차 시간 및 주차 요금");
            // System.out.println("총 주차 시간(분) : " + totalTime + "(분), 총 요금(원) : " + totalFare + "(원)");
            
            result[index][0] = Integer.parseInt(number);
            result[index][1] = totalFare;
            index++;
        }
        
        Arrays.sort(result, (i1, i2) -> i1[0] - i2[0]);
        
        int[] answer = new int[result.length];
        for(int i = 0; i < result.length; i++) {
            answer[i] = result[i][1];
        }
        
        return answer;
    }
    
    private static int convertTimeToMinute(int inHour, int outHour, int inMinute, int outMinute) {
        // System.out.println("입차 기록 : " + inHour + "시 " + inMinute + "분, 출차 기록 : " + outHour + "시 " + outMinute + "분");
        
        return ((outHour - inHour) * 60) + (outMinute - inMinute);
    }
}