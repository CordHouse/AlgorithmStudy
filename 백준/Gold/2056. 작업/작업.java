import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] time = new int[n+1];
        int[] count = new int[n+1];
        List<Integer>[] match = new ArrayList[n+1];
        Queue<Node> queue = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            match[i] = new ArrayList<>();
        }
        for(int i = 1; i <= n; i++) {
            String[] input = br.readLine().split(" ");
            time[i] = Integer.parseInt(input[0]);
            count[i] = Integer.parseInt(input[1]);
            if(input.length > 2) {
                for (int j = 2; j < input.length; j++) {
                    match[Integer.parseInt(input[j])].add(i);
                }
            }
        }

        int[] temp = new int[n+1];
        for(int i = 1; i <= n; i++) {
            temp[i] = time[i];

            if(count[i] == 0) {
                queue.add(new Node(i, match[i]));
            }
        }

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(int v : node.list) {
                count[v]--;

                temp[v] = Math.max(temp[v], temp[node.index] + time[v]);
                if(count[v] == 0) {
                    queue.add(new Node(v, match[v]));
                }
            }
        }

        int answer = 0;
        for(int v : temp) {
            answer = Math.max(answer, v);
        }
        System.out.println(answer);
    }
    static class Node{
        int index;
        List<Integer> list;
        public Node(int index, List<Integer> list) {
            this.index = index;
            this.list = list;
        }
    }
}