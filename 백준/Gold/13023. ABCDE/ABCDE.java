import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int n, m;
    private static int answer = 0;
    private static List<Integer>[] node;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        node = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            node[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int left = Integer.parseInt(line[0]);
            int right = Integer.parseInt(line[1]);
            node[left].add(right);
            node[right].add(left);
        }

        visit = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(answer == 1) {
                break;
            }
            dfs(1, i);
        }
        System.out.println(answer);
    }

    public static void dfs(int depth, int start) {
        if(depth == 5) {
            answer = 1;
            return;
        }

        visit[start] = true;
        for(int v : node[start]) {
            if(!visit[v]) {
                visit[v] = true;
                dfs(depth+1, v);
                visit[v] = false;
            }
        }
        visit[start] = false;
    }
}