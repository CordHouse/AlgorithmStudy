import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            list.get(n1).add(n2);
            list.get(n2).add(n1);
        }

        boolean[] visit = new boolean[n+1];
        int[] parent = new int[n+1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visit[1] = true;

        while(!queue.isEmpty()) {
            int root = queue.poll();

            for(int left : list.get(root)) {
                if(!visit[left]) {
                    visit[left] = true;
                    queue.add(left);
                    parent[left] = root;
                }
            }
        }

        for(int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }
}
