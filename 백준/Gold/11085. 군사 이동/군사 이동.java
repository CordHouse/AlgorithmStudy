import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        parent = new int[p];
        for(int i = 1; i < p; i++) {
            parent[i] = i;
        }

        PriorityQueue<Load> queue = new PriorityQueue<>((o1, o2) -> o2.width - o1.width);
        for(int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            queue.add(new Load(s, e, width));
        }

        while(!queue.isEmpty()) {
            Load load = queue.poll();

            if(find(c) == find(v)) {
                break;
            }
            union(load.s, load.e);
            answer = load.width;
        }

        System.out.println(answer);
    }

    public static void union(int s, int e) {
        int findS = find(s);
        int findE = find(e);
        if(findS < findE) {
            parent[findE] = findS;
        }
        else {
            parent[findS] = findE;
        }
    }
    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }

        return parent[num] = find(parent[num]);
    }

    static class Load {
        int s, e, width;
        public Load(int s, int e, int width) {
            this.s = s;
            this.e = e;
            this.width = width;
        }
    }
}
