import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());

        int line = 1;
        int prevSellCount = 0;
        String answer;

        while(true) {
            if(x <= line + prevSellCount) {
                if(line % 2 == 0) {
                    answer = (x - prevSellCount) + "/" + (line - (x - prevSellCount - 1));
                }
                else {
                    answer = (line - (x - prevSellCount - 1)) + "/" + (x - prevSellCount);
                }
                break;
            }
            else {
                prevSellCount += line;
                line++;
            }
        }
        System.out.println(answer);
    }
}