import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while(testcase --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 배열의 크기
            int d = Integer.parseInt(st.nextToken()); // 반복 횟수
            int c = Integer.parseInt(st.nextToken()); // start 시작점
            List<List<Node>> graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Node(a, s));
            }

            dijkstra(graph, n, c);
        }
    }

    public static void dijkstra(List<List<Node>> graph, int n, int start) {
        int count = 0;
        int time = 0;
        int[] arr = new int[n+1]; // 인덱스 1번부터 시작
        boolean[] visit = new boolean[n+1]; // 중복 방문 체크
        Arrays.fill(arr, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.sec - o2.sec);
        pq.add(new Node(start, 0));
        arr[start] = 0;

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();

            if(!visit[curNode.a]) {
                count++;
                visit[curNode.a] = true;
            }
            else {
                continue;
            }
            for(Node node : graph.get(curNode.a)) {
                if(!visit[node.a] && curNode.sec + node.sec < arr[node.a]) {
                    arr[node.a] = curNode.sec + node.sec;
                    pq.add(new Node(node.a, arr[node.a]));
                }
            }
        }

        for(int value : arr) {
            if(value == Integer.MAX_VALUE) {
                continue;
            }
            time = Math.max(time, value);
        }
        System.out.println(count + " " + time);
    }

    static class Node {
        int a, sec;

        public Node(int a, int sec) {
            this.a = a;
            this.sec = sec;
        }
    }
}
