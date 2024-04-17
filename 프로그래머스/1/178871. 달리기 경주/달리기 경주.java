import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> rank = new HashMap<>();
        for(int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
        }
        
        for(int i = 0; i < callings.length; i++) {
            String name = callings[i];
            int playerRank = rank.get(name);
            players[playerRank] = players[playerRank - 1];
            players[playerRank - 1] = name;
            rank.put(players[playerRank], rank.get(players[playerRank]) + 1);
            rank.put(name, rank.get(name) - 1);
        }
        
        return players;
    }
}