import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        Stack<Character> input = new Stack<>();

        int answer = 0;
        int tmp = 1;
        for(int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if(c == '(') {
                input.push(c);
                tmp *= 2;
            } else if(c == '[') {
                tmp *= 3;
                input.push(c);
            } else {
                if(c == ')') {
                    if(input.isEmpty() || input.peek() != '(') {
                        answer = 0;
                        break;
                    }
                    else if(line.charAt(i-1) == '(') {
                        answer += tmp;
                    }
                    input.pop();
                    tmp /= 2;
                } else if(c == ']') {
                    if(input.isEmpty() || input.peek() != '[') {
                        answer = 0;
                        break;
                    }
                    else if(line.charAt(i-1) == '[') {
                        answer += tmp;
                    }
                    input.pop();
                    tmp /= 3;
                }
            }
        }
        
        if(!input.isEmpty()) {
            answer = 0;
        }

        System.out.println(answer);
    }
}