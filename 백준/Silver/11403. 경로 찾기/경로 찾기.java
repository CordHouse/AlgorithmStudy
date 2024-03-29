import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] inputTable = new String[n][n];

        for(int i = 0; i < n; i++) {
            String[] splitValue = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                inputTable[i][j] = splitValue[j];
            }
        }

        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(inputTable[i][k].equals("1") && inputTable[k][j].equals("1")) {
                        inputTable[i][j] = "1";
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(inputTable[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
