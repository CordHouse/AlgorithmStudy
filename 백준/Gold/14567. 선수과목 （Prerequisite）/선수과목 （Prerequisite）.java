import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        Arrays.fill(arr, 1);

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.f - o2.f);
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            queue.add(new Node(f, s));
        }

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            arr[node.s] = Math.max(arr[node.s], arr[node.f] + 1);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }

    static class Node {
        int f;
        int s;
        public Node(int f, int s) {
            this.f = f;
            this.s = s;
        }
    }
}
