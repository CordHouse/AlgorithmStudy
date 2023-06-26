import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static int[] arr;
    private static boolean[] visit;
    private static boolean[] check;
    private static int n;
    private static List<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        visit = new boolean[n+1];
        check = new boolean[n+1];

        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(!check[arr[i]]) {
                check[arr[i]] = true;
            }
        }

        for(int i = 1; i <= n; i++) {
            if(check[i]) {
                visit[i] = true;
                dfs(i);
                visit[i] = false;
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for(int v : answer) {
            System.out.println(v);
        }
    }

    public static void dfs(int num) {
        if(visit[arr[num]]) {
            if(!answer.contains(num)) {
                answer.add(num);
            }
            return;
        }

        if(!visit[arr[num]] && check[arr[num]]) {
            visit[arr[num]] = true;
            dfs(arr[num]);
            visit[arr[num]] = false;
        }
    }
}