class Solution {
    public static int[][] graph;
    public static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        graph = new int[n + 1][n + 1];
        for(int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            
            // 간선을 임시로 끊음
            graph[v1][v2] = 0;
            graph[v2][v1] = 0;
            
            visited = new boolean[n + 1];
            int result = 0;
            for(int j = 1; j <= n; j++) {
                if(!visited[j]) {
                    visited[j] = true;
                    result = Math.abs(result - dfs(n, j, 1));
                }
            }

            // 두 전력망 송전탑 개수의 차이 구하기
            answer = Math.min(answer, result);
            
            // 임시로 끊은 간선을 다시 추가
            graph[v1][v2] = 1;
            graph[v2][v1] = 1;
        }
        
        return answer;
    }
    
    private static int dfs(int n, int v, int cnt) { 
        for(int i = 1; i <= n; i++) {
            if(graph[v][i] != 0 && !visited[i]) {
                visited[i] = true;
                cnt = dfs(n, i, cnt + 1);
            }
        }
        
        return cnt;
    }
}