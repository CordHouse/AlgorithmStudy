import java.util.*;
// 선물 이력, 선물 지수
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Node> nameTable = new HashMap<>();
        Map<String, Integer> index = new HashMap<>();
        int[][] records = new int[friends.length + 1][friends.length + 1];
        Map<String, Integer> getItem = new HashMap<>();
        
        // 초기화
        for(int i = 0; i < friends.length; i++) {
            nameTable.put(friends[i], new Node(0, 0, 0));
            index.put(friends[i], i+1);
            getItem.put(friends[i], 0);
        }
        
        // 준 선물, 받은 선물, 선물 지수 기록
        for(String gift : gifts) {
            StringTokenizer st = new StringTokenizer(gift);
            String give = st.nextToken();
            String take = st.nextToken();
            
            nameTable.get(give).give += 1;
            nameTable.get(take).take += 1;
            nameTable.get(give).point = nameTable.get(give).give - nameTable.get(give).take;
            nameTable.get(take).point = nameTable.get(take).give - nameTable.get(take).take;
            
            records[index.get(give)][index.get(take)]++;
        }
        
        // 누가 더 선물을 많이 받을까?
        for(int i = 1; i <= friends.length; i++) {
            for(int j = 1; j <= friends.length; j++) {
                if(i != j) {
                    // 주고 받은 기록이 없거나, 주고 받은 횟수가 같다면
                    if((records[i][j] == 0 && records[j][i] == 0) || (records[i][j] == records[j][i])){
                        if(nameTable.get(friends[i-1]).point > nameTable.get(friends[j-1]).point) {
                            getItem.put(friends[i-1], getItem.get(friends[i-1]) + 1);
                        }
                    }
                    // 두 사람이 선물을 주고 받은 경우
                    else if(records[i][j] != 0 || records[j][i] != 0) {
                        if(records[i][j] > records[j][i]) {
                            getItem.put(friends[i-1], getItem.get(friends[i-1]) + 1);
                        }
                    }                    
                }
            }
        }
        
        for(String name : friends) {
            answer = Math.max(answer, getItem.get(name));
        }
        return answer;
    }
}

class Node {
    int give = 0;
    int take = 0;
    int point = 0;
    
    public Node(int give, int take, int point) {
        this.give = give;
        this.take = take;
        this.point = point;
    }
}