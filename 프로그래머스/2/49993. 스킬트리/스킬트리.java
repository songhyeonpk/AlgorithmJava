class Solution {
    public int solution(String skill, String[] skill_trees) {
        int[] skills = new int[26];
        for(int i = 0; i < skill.length(); i++) {
            char sk = skill.charAt(i);
            skills[sk - 'A'] = i + 1;
        }
        
        int len = 0;
        int answer = 0;
        while(len < skill_trees.length) {
            String st = skill_trees[len++];
            
            int cnt = 1;
            boolean check = true;
            for(int i = 0; i < st.length(); i++) {
                char sk = st.charAt(i);
                int skillCnt = skills[sk - 'A'];
                if(skillCnt == 0) {
                    continue;
                }
                
                if(skillCnt != cnt) {
                    check = false;
                    break;
                }
                
                cnt += 1;
            }
            
            if(check) {
                answer += 1;
            }
        }
        
        return answer;
    }
}