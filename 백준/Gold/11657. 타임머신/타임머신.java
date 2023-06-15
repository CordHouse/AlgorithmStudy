import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int INF = 100_000_000;
    private static int n;
    private static int m;
    private static List<Node> graph = new ArrayList<>();
    private static long[] arr;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        arr = new long[n+1];
        Arrays.fill(arr, INF);

        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int w = Integer.parseInt(line[1]);
            int v = Integer.parseInt(line[2]);
            graph.add(new Node(u, w, v));
        }

        bellmanFord(1);

        if(answer == -1) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= n; i++) {
            if(arr[i] == INF) {
                sb.append("-1\n");
                continue;
            }
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void bellmanFord(int start) {
        arr[start] = 0;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Node node = graph.get(j);

                if(arr[node.u] != INF && arr[node.w] > arr[node.u] + node.v) {
                    arr[node.w] = arr[node.u] + node.v;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            Node node = graph.get(i);
            if(arr[node.u] != INF && arr[node.w] > arr[node.u] + node.v) {
                answer = -1;
                return;
            }
        }
    }

    static class Node {
        int u;
        int w;
        int v;
        public Node(int u, int w, int v) {
            this.u = u;
            this.w = w;
            this.v = v;
        }
    }
}