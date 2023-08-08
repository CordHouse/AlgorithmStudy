import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static String[] type;
    private static List<Node> list = new ArrayList<>();
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        type = new String[n+1];
        parent = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            type[i] = st.nextToken();
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            list.add(new Node(s, e, distance));
        }

        list.sort((o1, o2) -> o1.distance - o2.distance);

        int answer = 0;
        int count = 0;
        for(int i = 0; i < list.size(); i++) {
            Node node = list.get(i);

            if(find(node.s) != find(node.e)) {
                if(!type[node.s].equals(type[node.e])) {
                    count++;
                    answer += node.distance;

                    union(node.s, node.e);
                }
            }
        }

        if(n-1 != count) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }

        return parent[num] = find(parent[num]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            parent[y] = x;
        }
    }

    static class Node {
        int s;
        int e;
        int distance;

        public Node(int s, int e, int distance) {
            this.s = s;
            this.e = e;
            this.distance = distance;
        }
    }
}
