import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        dp = new int[n+1][m+1];

        for(int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 1; j <= m; j++) {
                dp[i][j] = Integer.parseInt(line[j-1]);
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                dp[i][j] += Math.max(dp[i][j-1], Math.max(dp[i-1][j], dp[i-1][j-1]));
            }
        }
        System.out.println(dp[n][m]);
    }
}
