import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static int n, m;
    private static List<Node>[] root;
    private static int[] load;
    private static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        load = new int[n+1];
        Arrays.fill(load, Integer.MAX_VALUE);

        root = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            root[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int cur = Integer.parseInt(line[0]);
            int next = Integer.parseInt(line[1]);
            int value = Integer.parseInt(line[2]);

            root[cur].add(new Node(next, value));
            root[next].add(new Node(cur, value));
        }

        check = new boolean[n+1];
        dijkstra(1);
        System.out.println(load[n]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
        queue.add(new Node(start, 0));
        load[start] = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(!check[node.next]) {
                check[node.next] = true;
            }

            for(Node nextNode : root[node.next]) {
                if(!check[nextNode.next] && load[nextNode.next] >= node.value + nextNode.value) {
                    load[nextNode.next] = node.value + nextNode.value;
                    queue.add(new Node(nextNode.next, load[nextNode.next]));
                }
            }
        }
    }

    static class Node{
        int next;
        int value;
        public Node(int next, int value) {
            this.next = next;
            this.value = value;
        }
    }
}
