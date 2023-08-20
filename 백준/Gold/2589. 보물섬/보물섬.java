import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static String[][] map;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static int r, c;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new String[r][c];
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        for(int i = 0; i < r; i++) {
            String[] line = br.readLine().split("");
            for(int j = 0; j < c; j++) {
                map[i][j] = line[j];
                if(map[i][j].equals("L")) {
                    queue.add(new Node(i, j, 0, new boolean[r][c]));
                }
            }
        }

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int count = 0;

            if(!node.visit[node.x][node.y]) {
                node.visit[node.x][node.y] = true;
            }

            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;

                if(nx >= 0 && ny >= 0 && nx < r && ny < c) {
                    if(!node.visit[nx][ny] && map[nx][ny].equals("L")) {
                        node.visit[nx][ny] = true;
                        queue.add(new Node(nx, ny, node.distance+1, node.visit));
                        count++;
                    }
                }
            }
            if(count == 0) {
                answer = Math.max(answer, node.distance);
            }
        }

        System.out.println(answer);
    }

    static class Node {
        int x, y;
        int distance;
        boolean[][] visit;

        public Node(int x, int y, int distance, boolean[][] visit) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.visit = visit;
        }
    }
}