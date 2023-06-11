import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    private static List<Node>[] list;
    private static boolean[] check;
    private static boolean[] visit;
    private static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        arr = new long[n];
        Arrays.fill(arr, Long.MAX_VALUE);

        // Step 1. 보이지 않는 위치 설정하기
        check = new boolean[n];
        list = new ArrayList[n];
        input = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            if(input[i].equals("0")) {
                check[i] = true;
            }
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            long distance = Long.parseLong(line[2]);
            list[start].add(new Node(end, distance));
            list[end].add(new Node(start, distance));
        }

        check[n-1] = true;
        visit = new boolean[n];
        dijkstra(0);

        long answer = arr[n-1];
        if(answer == Long.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> (int)o1.distance - (int)o2.distance);
        queue.add(new Node(start, 0));
        arr[start] = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(!check[node.end]) {
                return;
            }
            if(visit[node.end]) {
                continue;
            }
            else {
                visit[node.end] = true;
            }

            for(Node n : list[node.end]) {
                if(check[n.end] && !visit[n.end] && arr[n.end] > n.distance + node.distance) {
                    arr[n.end] = n.distance + node.distance;
                    queue.add(new Node(n.end, arr[n.end]));
                }
            }
        }
    }

    static class Node {
        int end;
        long distance;

        public Node(int end, long distance) {
            this.end = end;
            this.distance = distance;
        }
    }
}