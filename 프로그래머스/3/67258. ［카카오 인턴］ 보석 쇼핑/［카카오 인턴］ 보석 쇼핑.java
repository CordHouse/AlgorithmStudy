import java.util.*;

class Solution {
    static int[][] table;
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int gemsLen = gems.length;
        
        List<String> saveGems = new LinkedList<>();
        
        for(String gem : gems) {
            if(!saveGems.contains(gem)) {
                saveGems.add(gem);
            }
        }
        int stop = saveGems.size();
        int start = 0;
        int range = Integer.MAX_VALUE;
        Map<String, Integer> table = new HashMap<>();
        for(int end = 0; end < gemsLen; end++) {
            table.put(gems[end], table.getOrDefault(gems[end], 0) + 1);
            
            while(table.get(gems[start]) > 1) {
                table.put(gems[start], table.get(gems[start]) - 1);
                start++;
            }
            
            if(stop == table.size() && (end - start) < range) {
                range = end - start;
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
        }
        return answer;
    }
}