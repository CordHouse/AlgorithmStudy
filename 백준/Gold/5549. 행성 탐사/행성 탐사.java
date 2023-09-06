import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, k;
    private static int[][][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        k = Integer.parseInt(br.readLine());
        int layer = 0;
        map = new int[3][n+1][m+1];
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                char c = line.charAt(j - 1);
                if(c == 'J') layer = 0;
                else if(c == 'O') layer = 1;
                else layer = 2;
                map[layer][i][j] = 1;
            }
        }

        for(int h = 0; h < 3; h++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    map[h][i][j] += map[h][i-1][j] + map[h][i][j-1] - map[h][i-1][j-1];
                }
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            search(a, b, c, d);
        }
    }

    public static void search(int a, int b, int c, int d) {
        int J = map[0][c][d] - map[0][a-1][d] - map[0][c][b-1] + map[0][a-1][b-1];
        int O = map[1][c][d] - map[1][a-1][d] - map[1][c][b-1] + map[1][a-1][b-1];
        int I = map[2][c][d] - map[2][a-1][d] - map[2][c][b-1] + map[2][a-1][b-1];

        System.out.println(J+" "+O+" "+I);
    }
}