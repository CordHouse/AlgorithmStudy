import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent, cost;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int m = Integer.parseInt(st.nextToken()); // 친구관계 수
        int k = Integer.parseInt(st.nextToken()); // 보유한 돈


        parent = new int[n+1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = find(Integer.parseInt(st.nextToken()));
            int n2 = find(Integer.parseInt(st.nextToken()));
            if(n1 != n2) {
                union(n1, n2);
            }
        }

        boolean[] visit = new boolean[n];
        for(int i = 1; i <= n; i++) {
            int index = find(i) - 1;
            if(visit[index]) {
                continue;
            }
            if(k - cost[index] >= 0) {
                visit[index] = true;
                k -= cost[index];
                answer += cost[index];
            }
            else {
                System.out.println("Oh no");
                return;
            }
        }
        System.out.println(answer);
    }

    public static void union(int n1, int n2) {
        if(cost[n1 - 1] < cost[n2 - 1]) {
            parent[n2] = n1;
        }
        else {
            parent[n1] = n2;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }
}