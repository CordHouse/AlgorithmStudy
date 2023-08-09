import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int cycle = 0; cycle < t; cycle++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            map = new int[n+1][n+1];
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    map[i][j] = iEqualJ(i, j);
                }
            }

            for (int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());

                map[s][e] = distance;
                map[e][s] = distance;
            }

            int k = Integer.parseInt(br.readLine());
            int[] player = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for(int z = 1; z <= n; z++) {
                for(int i = 1; i <= n; i++) {
                    for(int j = 1; j <= n; j++) {
                        if(i != j) {
                            map[i][j] = Math.min(map[i][j], map[i][z] + map[z][j]);
                        }
                    }
                }
            }

            int index = -1;
            int min = INF;
            for(int i = 1; i<= n; i++) {
                int sum = 0;

                for(int person : player) {
                    sum += map[person][i];
                }

                if(sum < min) {
                    index = i;
                    min = sum;
                }
            }

            System.out.println(index);
        }

    }

    public static int iEqualJ(int i, int j) {
        if(i == j) {
            return 0;
        }
        return INF;
    }
}
