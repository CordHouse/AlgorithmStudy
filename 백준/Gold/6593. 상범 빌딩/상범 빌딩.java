import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {1, 0, -1, 0, 0, 0}, dy = {0, -1, 0, 1, 0, 0}, dz = {0, 0, 0, 0, 1, -1};
    private static String[][][] map;
    private static boolean[][][] visit;
    private static int sx, sy, sz;
    private static int L, R, C, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            answer = 0;
            sx = 0;
            sy = 0;
            sz = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (exit()) {
                System.out.print(sb);
                return;
            }

            map = new String[L][R][C];
            for(int l = 0; l < L; l++) {
                for(int r = 0; r < R; r++) {
                    String[] s = br.readLine().split("");
                    for(int c = 0; c < C; c++) {
                        if(s[c].equals("S")) {
                            sx = r;
                            sy = c;
                            sz = l;
                        }
                        map[l][r][c] = s[c];
                    }
                }
                br.readLine();
            }

            visit = new boolean[L][R][C];
            bfs();
            if(answer != 0) {
                sb.append("Escaped in ").append(answer).append(" minute(s).\n");
                continue;
            }
            sb.append("Trapped!\n");
        }
    }

    public static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(sx, sy, sz, 0));
        visit[sz][sx][sy] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(map[node.z][node.x][node.y].equals("E")) {
                answer = node.time;
                return;
            }

            for(int i = 0; i < 6; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nz = node.z + dz[i];

                if(check(nx, ny, nz) && !visit[nz][nx][ny] && !map[nz][nx][ny].equals("#")) {
                    visit[nz][nx][ny] = true;
                    queue.add(new Node(nx, ny, nz, node.time+1));
                }
            }
        }
    }

    public static boolean check(int x, int y, int z) {
        return x >= 0 && y >= 0 && z >= 0 && x < R && y < C && z < L;
    }

    public static boolean exit() {
        return L == 0 && R == 0 && C == 0;
    }

    static class Node {
        int x, y, z, time;
        public Node(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }
}
