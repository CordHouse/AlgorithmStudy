import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[][] map;
    private static boolean[][] visit;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int target = 1;
        visit = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visit[i][j] && map[i][j] != 0) {
                    ireland(i, j, target++);
                }
            }
        }

        visit = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visit[i][j] && map[i][j] > 0) {
                    visit[i][j] = true;
                    bridge(i, j, map[i][j]);
                    visit = new boolean[n][n];
                }
            }
        }
        System.out.println(min);
    }

    public static void bridge(int x, int y, int num) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(node.length >= min) {
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if((nx < 0 || ny < 0 || nx >= n || ny >= n) || visit[nx][ny] || map[nx][ny] == num) {
                    continue;
                }

                if(map[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny, node.length+1));
                }
                else {
                    min = Math.min(min, node.length);
                }
            }
        }
    }

    public static void ireland(int x, int y, int target) {
        visit[x][y] = true;
        map[x][y] = target;

        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if(!visit[nx][ny] && map[nx][ny] != 0) {
                    ireland(nx, ny, target);
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int length;

        public Node(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
}
