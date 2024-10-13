import java.util.*;

class Solution {
    private String[] posSplit, video;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        posSplit = pos.split(":");
        video = video_len.split(":");
        
        for(String cmd : commands) {
            isOpen(op_start, op_end);
            if(cmd.equals("prev")) {
                prev();
            }
            else if(cmd.equals("next")) {                
                next();
            }
        }
        isOpen(op_start, op_end);
        
        if(posSplit[0].length() == 1 && posSplit[1].length() == 1) {
            answer = "0" + posSplit[0] + ":0" + posSplit[1];
        }
        else if(posSplit[0].length() == 1 && posSplit[1].length() != 1) {
            answer = "0" + posSplit[0] + ":" + posSplit[1];
        }
        else if(posSplit[0].length() != 1 && posSplit[1].length() == 1) {
            answer = posSplit[0] + ":0" + posSplit[1];
        }
        else {
            answer = posSplit[0] + ":" + posSplit[1];
        }
        return answer;
    }
    private void prev() {
        if(posSplit[0].equals("0") && Integer.parseInt(posSplit[1]) - 10 <= 0) {            
            posSplit[1] = "0";
        }
        else if(Integer.parseInt(posSplit[1]) - 10 < 0) {
            posSplit[0] = String.valueOf(Integer.parseInt(posSplit[0]) - 1);
            posSplit[1] = String.valueOf(Integer.parseInt(posSplit[1]) + 60 - 10);
        }
        else if(Integer.parseInt(posSplit[1]) - 10 == 0) {
            posSplit[1] = "0";
        }
        else {
            posSplit[1] = String.valueOf(Integer.parseInt(posSplit[1]) - 10);
        }
    }

    private void next() {
        if(Integer.parseInt(video[0]) * 60 + Integer.parseInt(video[1]) <= Integer.parseInt(posSplit[0]) * 60 + Integer.parseInt(posSplit[1]) + 10) {
                posSplit[0] = String.valueOf(Integer.parseInt(video[0]));
                posSplit[1] = String.valueOf(Integer.parseInt(video[1]));
            }
        else if(Integer.parseInt(posSplit[1]) + 10 > 60) {
            posSplit[0] = String.valueOf(Integer.parseInt(posSplit[0]) + 1);
            posSplit[1] = String.valueOf(Integer.parseInt(posSplit[1]) + 10 - 60);            
        }
        else if(Integer.parseInt(posSplit[1]) + 10 == 60) {
            posSplit[0] = String.valueOf(Integer.parseInt(posSplit[0]) + 1);
            posSplit[1] = "0";
        }
        else {
            posSplit[1] = String.valueOf(Integer.parseInt(posSplit[1]) + 10);
        }
    }

    private void isOpen(String op_start, String op_end) {
        String[] opSplit = op_start.split(":");
        String[] endSplit = op_end.split(":");
        
        int op = Integer.parseInt(opSplit[0] + opSplit[1]);
        int end = Integer.parseInt(endSplit[0] + endSplit[1]);
        int cur = Integer.parseInt(posSplit[0] + posSplit[1]);
        
        if(opSplit[1].length() == 1) {
            op = Integer.parseInt(opSplit[0] + "0" + opSplit[1]);
        }
        if(endSplit[1].length() == 1) {
            end = Integer.parseInt(endSplit[0] + "0" + endSplit[1]);
        }
        if(posSplit[1].length() == 1) {
            cur = Integer.parseInt(posSplit[0] + "0" + posSplit[1]);
        }
        
        if(op <= cur && cur <= end) {
            posSplit[0] = String.valueOf(Integer.parseInt(endSplit[0]));
            posSplit[1] = String.valueOf(Integer.parseInt(endSplit[1]));
        }
    }
}