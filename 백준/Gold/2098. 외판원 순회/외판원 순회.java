import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int n;
    private static final int INF = 10_000_000;
    private static int[][] dp, map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < input.length; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        dp = new int[n][(1<<n) - 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(0, 1));
    }

    public static int dfs(int now, int visit) {
        if(visit == (1 << n) - 1) {
            if(map[now][0] == 0) {
                return INF;
            }
            return map[now][0];
        }

        if(dp[now][visit] != -1) {
            return dp[now][visit];
        }

        dp[now][visit] = INF;

        for(int i = 0; i < n; i++) {
            if((visit & (1 << i)) == 0 && map[now][i] != 0) {
                dp[now][visit] = Math.min(dfs(i, visit | (1 << i)) + map[now][i], dp[now][visit]);
            }
        }
        return dp[now][visit];
    }
}