import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n;
    private static int[][] cost;
    private static boolean[] visit;
    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];

        // Step 1. 입력을 배열로 만들어 저장한다. (가중치)
        for(int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(line[j]);
            }
        }

        // Step 2. dfs 시작
        visit = new boolean[n];
        for(int i = 0; i < n; i++) {
            visit[i] = true;
            dfs(0, i, i, 0);
            visit[i] = false;
        }
        System.out.println(answer);
    }

    public static void dfs(int depth, int start, int cur, int sum) {
        if(depth == n-1) {
            if(cost[cur][start] != 0) {
                sum += cost[cur][start];
                answer = Math.min(answer, sum);
            }
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!visit[i] && cost[cur][i] != 0) {
                visit[i] = true;
                dfs(depth+1, start, i, sum + cost[cur][i]);
                visit[i] = false;
            }
        }
    }
}