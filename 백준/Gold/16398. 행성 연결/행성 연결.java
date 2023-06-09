import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static int n;
    private static int[] parent;
    private static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.v - o2.v);
        for(int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 1; j <= n; j++) {
                queue.add(new Node(i, j, Integer.parseInt(line[j-1])));
            }
        }

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            int start = find(node.s);
            int end = find(node.e);

            if(start != end) {
                answer += node.v;
                union(start, end);
            }
        }
        System.out.println(answer);
    }
    public static void union(int start, int end) {
        if(start == end) {
            return;
        }

        if(start < end) {
            parent[end] = start;
        }
        else {
            parent[start] = end;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }

    static class Node {
        int s;
        int e;
        int v;
        public Node(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }
}
