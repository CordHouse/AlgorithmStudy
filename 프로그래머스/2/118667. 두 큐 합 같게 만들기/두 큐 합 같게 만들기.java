import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;        
        int[] mixQueue = new int[queue1.length + queue2.length];
        
        for(int i = 0; i < queue1.length; i++) {
            mixQueue[i] = queue1[i];
            mixQueue[i+queue1.length] = queue2[i];
        }
        
        long total = sum(mixQueue);     
        if(total % 2 == 1) {
            return -1;
        }
        
        int left = 0, right = queue1.length - 1;
        long s1 = sum(queue1);
        long s2 = sum(queue2);
        while(s1 != s2) {
            if(s1 == 0 || s2 == 0) {
                return -1;
            }
            if(s1 < s2) {
                right++;
                s1 += mixQueue[right % mixQueue.length];
                s2 -= mixQueue[right % mixQueue.length];
            }
            else if(s1 > s2) {
                s1 -= mixQueue[left % mixQueue.length];
                s2 += mixQueue[left % mixQueue.length];
                left++;
            }
            if(left > mixQueue.length && right > mixQueue.length) return -1;
            
            answer++;
        }
        
        return answer;
    }
    
    public int sum(int[] arr) {
        int sum = 0;
        for(int v : arr) {
            sum += v;
        }
        return sum;
    }
}