import java.util.*;

class Solution {
    public static boolean[] visited;
    public static List<List<Integer>> graph = new ArrayList<>();
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < computers.length; i++) {
            for(int j = 0; j < computers[i].length; j++) {
                if(i == j) {
                    continue;
                }
                
                if(computers[i][j] == 1) {
                    graph.get(i).add(j);
                }
            }
        }
        
        int answer = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                answer += dfs(i);
            }
        }
        
        return answer;
    }
    
    public static int dfs(int v) {
        List<Integer> list = graph.get(v);
        
        for(int i = 0; i < list.size(); i++) {
            int nv = list.get(i);
            
            if(!visited[nv]) {
                visited[nv] = true;
                dfs(nv);
            }
        }
        
        return 1;
    }
}