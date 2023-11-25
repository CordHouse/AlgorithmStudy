import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] number = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i = 0; i < number.length; i++) {
            s = s.replaceAll(number[i], String.valueOf(i));
        }
        answer = Integer.parseInt(s);
        return answer;
    }
}