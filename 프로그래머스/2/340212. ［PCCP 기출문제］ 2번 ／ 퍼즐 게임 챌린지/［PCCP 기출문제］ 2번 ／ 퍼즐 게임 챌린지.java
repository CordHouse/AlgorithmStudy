import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        long answer = Long.MAX_VALUE;
        
        int right = 0;
        int left = Integer.MAX_VALUE;
        
        for(int value : diffs) {
            right = Math.max(right, value);
            left = Math.min(left, value);
        }
        
        while(left <= right) {
            int mid = (left + right) / 2;
            long beforeTime = 0;
            long curTime = 0;
            
            for(int i = 0; i < diffs.length; i++) {
                if(diffs[i] <= mid) {
                    curTime += times[i];
                }
                else {
                    curTime += (times[i] + beforeTime) * (diffs[i] - mid) + times[i];
                }
                beforeTime = times[i];
            }
            
            if(curTime <= limit) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        
        return (int) answer;
    }
}