import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, answer = 0;
    private static int[][] map;
    private static int[][] dp;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        dp = new int[n+1][n+1];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    public static int dfs(int x, int y) {
        if(dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx > 0 && ny > 0 && nx <= n && ny <= n) {
                if(map[x][y] < map[nx][ny]) {
                    dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
                }
            }
        }
        return dp[x][y];
    }
}