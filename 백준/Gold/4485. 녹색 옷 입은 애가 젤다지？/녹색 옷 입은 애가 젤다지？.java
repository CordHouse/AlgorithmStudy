import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] map, cloneMap;
    private static boolean[][] visit;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 1;

        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        do {
            map = new int[n][n];
            cloneMap = new int[n][n];
            for(int i = 0; i < n; i++) {
                Arrays.fill(cloneMap[i], Integer.MAX_VALUE);
            }

            visit = new boolean[n][n];
            StringTokenizer st;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(0, 0);
            sb.append("Problem ").append(count).append(": ").append(cloneMap[n-1][n-1]).append("\n");
            n = Integer.parseInt(br.readLine());
            count++;
        } while(n != 0);
        System.out.println(sb);
    }

    public static void bfs(int x, int y) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.sum - o2.sum);
        visit[0][0] = true;
        queue.add(new Node(x, y, map[0][0]));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!visit[nx][ny] && cloneMap[nx][ny] > map[nx][ny] + node.sum) {
                        cloneMap[nx][ny] = map[nx][ny] + node.sum;
                        visit[nx][ny] = true;
                        queue.add(new Node(nx, ny, cloneMap[nx][ny]));
                    }
                }
            }
        }
    }

    static class Node {
        int x, y;
        int sum;
        public Node(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }
}