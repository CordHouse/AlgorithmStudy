import java.util.*;

class Solution {
    int alpMax, copMax;
    int[][] dp;
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        // 최대 알고력과 코딩력 찾기 (종료 지점)
        for(int i = 0; i < problems.length; i++) {            
            if(alpMax < problems[i][0]) {
                alpMax = problems[i][0];
            }
            if(copMax < problems[i][1]) {
                copMax = problems[i][1];
            }
        }
        
        if(alp > alpMax && cop > copMax) {
            return 0;
        }
        if(alp > alpMax) {
            alp = alpMax;
        }
        if(cop > copMax) {
            cop = copMax;
        }
        
        dp = new int[alpMax+2][copMax+2];
        for(int i = alp; i < alpMax + 1; i++) {
            for(int j = cop; j < copMax + 1; j++) {
                dp[i][j] = 1_000_000;
            }
        }
        
        dp[alp][cop] = 0;
        
        for(int i = alp; i <= alpMax; i++) {
            for(int j = cop; j <= copMax; j++) {
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                
                for(int[] pro : problems) {
                    if(i >= pro[0] && j >= pro[1]) {
                        int newAlp = Math.min(i+pro[2], alpMax);
                        int newCop = Math.min(j+pro[3], copMax);
                        
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + pro[4]);
                    }
                }
            }
        }
        
        answer = dp[alpMax][copMax];
        
        return answer;
    }    
}