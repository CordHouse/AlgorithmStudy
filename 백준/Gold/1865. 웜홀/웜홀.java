import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int INF = 10_000_000;
    private static int n, m, w;
    private static List<Node>[] graph;
    private static int[] value;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < testCase; tc++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
            w = Integer.parseInt(input[2]);

            graph = new ArrayList[n+1];
            for(int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i < m+w; i++) {
                String[] line = br.readLine().split(" ");
                int s = Integer.parseInt(line[0]);
                int e = Integer.parseInt(line[1]);
                int t = Integer.parseInt(line[2]);
                if(i < m) {
                    graph[s].add(new Node(e, t));
                    graph[e].add(new Node(s, t));
                    continue;
                }
                graph[s].add(new Node(e, -t));
            }

            value = new int[n+1];
            System.out.println(bellmanFord() ? "YES" : "NO");
        }
    }

    public static boolean bellmanFord() {
        Arrays.fill(value, INF);
        boolean check = false;
        value[1] = 0;

        for(int i = 1; i < n; i++) {
            check = false;
            for(int j = 1; j <= n; j++) {
                for(Node node : graph[j]) {
                    if (value[j] != Integer.MAX_VALUE && value[node.e] > value[j] + node.t) {
                        value[node.e] = value[j] + node.t;
                        check = true;
                    }
                }
            }

            if(!check) {
                break;
            }
        }
        return check;
    }

    static class Node {
        int e;
        int t;

        public Node(int e, int t) {
            this.e = e;
            this.t = t;
        }
    }
}