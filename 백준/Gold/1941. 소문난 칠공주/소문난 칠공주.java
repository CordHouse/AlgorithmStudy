import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static final int SIZE = 5;
    private static String[][] map = new String[SIZE][SIZE];
    private static int[] cx = new int[25], cy = new int[25];
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < SIZE; i++) {
            String[] line = br.readLine().split("");
            for(int j = 0; j < SIZE; j++) {
                map[i][j] = line[j];
            }
        }

        for(int i = 0; i < 25; i++) {
            cx[i] = i % 5;
            cy[i] = i / 5;
        }

        combination(new int[7], 0, 0, 7);
        System.out.println(answer);
    }

    public static void combination(int[] comb, int index, int depth, int left) {
        if(left == 0) {
            bfs(comb);
            return;
        }

        if(depth == 25) {
            return;
        }

        comb[index] = depth;
        combination(comb, index+1, depth+1, left-1); // 선택한 경우
        combination(comb, index, depth+1, left); // 선택하지 않은 경우
    }

    public static void bfs(int[] comb) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[7];

        visit[0] = true;
        queue.add(comb[0]);
        int cnt = 1, sCount = 0;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if(map[cy[cur]][cx[cur]].equals("S")) {
                sCount++;
            }

            for(int i = 0; i < 4; i++) {
                for(int next = 1; next < 7; next++) {
                    if(!visit[next] && cx[cur]+dx[i] == cx[comb[next]] && cy[cur]+dy[i] == cy[comb[next]]) {
                        visit[next] = true;
                        queue.add(comb[next]);
                        cnt++;
                    }
                }
            }
        }

        if(cnt == 7) {
            if(sCount >= 4) {
                answer++;
            }
        }
    }
}