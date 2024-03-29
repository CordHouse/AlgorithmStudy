import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        while(m --> 0) {
            st = new StringTokenizer(br.readLine());
            int o1 = Integer.parseInt(st.nextToken());
            int o2 = Integer.parseInt(st.nextToken());

            union(find(o1), find(o2));
        }

        HashSet<Integer> set = new HashSet<>();
        for(int v : parent) {
            if(v != 0) {
                set.add(find(v));
            }
        }
        System.out.println(set.size());
    }

    static void union(int o1, int o2) {
        if(o1 < o2) {
            parent[o2] = o1;
        }
        else {
            parent[o1] = o2;
        }
    }

    static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }
}
