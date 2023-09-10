import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer>[] node;
    private static boolean[] visit;
    private static int[] arr;
    private static int n, s, d, tmp = 0, answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 노드의 개수
        s = Integer.parseInt(st.nextToken()); // 케니소프트 위치
        d = Integer.parseInt(st.nextToken()); // 힘

        node = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }

        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            node[start].add(end);
            node[end].add(start);
        }

        arr = new int[n+1];
        visit = new boolean[n+1];
        tree(s);
        System.out.println(answer * 2);
    }

    public static int tree(int start) {
        visit[start] = true;
        for(int next : node[start]) {
            if(!visit[next]) {
                arr[start] = Math.max(arr[start], tree(next) + 1);
            }
        }

        if(start != s && arr[start] >= d) {
            answer++;
        }
        return arr[start];
    }
}