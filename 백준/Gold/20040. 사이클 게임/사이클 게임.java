import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int num1 = Integer.parseInt(line[0]);
            int num2 = Integer.parseInt(line[1]);
            if(find(num1) == find(num2)) {
                System.out.println(i+1);
                return;
            }
            union(num1, num2);
        }

        System.out.println(0);
    }

    public static void union(int num1, int num2) {
        int o1 = find(num1);
        int o2 = find(num2);

        if(parent[o1] < parent[o2]) {
            parent[o2] = o1;
        }
        else {
            parent[o1] = o2;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }
}