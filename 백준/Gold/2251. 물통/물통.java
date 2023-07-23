import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Set<Integer> set = new HashSet<>();
    private static int[] input;
    private static boolean[][] visit;
    private static List<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        visit = new boolean[201][201];

        dfs(0, 0, input[2]);

        answer.addAll(set);
        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        for(int v : answer) {
            sb.append(v).append(" ");
        }

        System.out.println(sb);
    }
    public static void dfs(int a, int b, int c) {
        if(visit[a][b]) {
            return;
        }
        if(a == 0) {
            set.add(c);
        }
        visit[a][b] = true;

        if(a + b > input[1]) {
            dfs((a+b)-input[1], input[1], c);
        }
        else {
            dfs(0, a+b, c);
        }

        if(b + a > input[0]) {
            dfs(input[0], (b+a)-input[0], c);
        }
        else {
            dfs(a+b, 0, c);
        }

        if(c + a > input[0]) {
            dfs(input[0], b, (c+a)-input[0]);
        }
        else {
            dfs(c+a, b, 0);
        }

        if(c + b > input[1]) {
            dfs(a, input[1], (c+b)-input[1]);
        }
        else {
            dfs(a, c+b, 0);
        }

        dfs(a, 0, b+c);
        dfs(0, b, a+c);
    }
}
