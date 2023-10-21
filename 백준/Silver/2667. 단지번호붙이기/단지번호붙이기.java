import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] map;
    static Boolean[][] check;
    static int[] build_Count;
    static int build_Num = 0, loop;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loop = Integer.parseInt(br.readLine());

        map = new int[loop][loop];
        check = new Boolean[loop][loop];
        build_Count = new int[loop * loop]; // 얘는 왜 이렇게 잡지?

        for (int i = 0; i < loop; i++) {
            String st = br.readLine();
            for (int j = 0; j < loop; j++) {
                map[i][j] = st.charAt(j) - '0';
                check[i][j] = Boolean.FALSE;
            }
        }

        for (int i = 0; i < loop; i++) {
            for (int j = 0; j < loop; j++) {
                if (map[i][j] == 1 && !check[i][j]) {
                    build_Num++;
                    DFS(i, j);
                }
            }
        }
        Arrays.sort(build_Count);
        System.out.println(build_Num);
        for(int i = 0; i<build_Count.length; i++){
            if(build_Count[i] == 0)
                continue;
            System.out.println(build_Count[i]);
        }
    }

    static void DFS(int x, int y) {
        check[x][y] = Boolean.TRUE;
        build_Count[build_Num]++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < loop && ny < loop) {
                if (map[nx][ny] == 1 && !check[nx][ny]) {
                    DFS(nx, ny);
                }
            }
        }
    }
}
