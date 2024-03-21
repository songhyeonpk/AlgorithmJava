class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int startTime = 1;
        int endTime = attacks[attacks.length - 1][0];
        int exhibitionTime = bandage[0];
        int recoveryOfSecond = bandage[1];
        int addRecovery = bandage[2];
        
        int maxHealth = health;
        int successCount = 0;
        while(startTime < endTime) {
            if(health <= 0) {
                return -1;
            }
            
            boolean isAttacked = false;
            for(int i = 0; i < attacks.length - 1; i++) {
                int attackTime = attacks[i][0];
                int demage = attacks[i][1];
                
                if(startTime == attackTime) {
                    health -= demage;
                    
                    isAttacked = true;
                    successCount = 0;
                    break;
                }
            }
            
            if(!isAttacked) {    
                successCount++;
                health += recoveryOfSecond;
                
                if(successCount == exhibitionTime) {
                    successCount = 0;
                    health += addRecovery;
                }
                
                if(maxHealth < health) {
                    health = maxHealth;
                }
            }
            
            startTime++;
        }
        
        health -= attacks[attacks.length - 1][1];
        if(health <= 0) {
            return -1;
        }
        
        return health;
    }
}