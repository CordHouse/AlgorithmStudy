import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m, answer;
    private static List<Integer>[] front;
    private static List<Integer>[] back;
    private static Set<Integer> set;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]); // 학생들 인원 수
        m = Integer.parseInt(input[1]); // 비교 횟수

        front = new ArrayList[n+1];
        back = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            front[i] = new ArrayList<>();
            back[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);

            front[a].add(b);
            back[b].add(a);
        }

        for(int i = 1; i <= n; i++) {
            function(i);
        }
        System.out.println(answer);
    }

    public static void search(Queue<List<Integer>> queue, boolean type) {
        while(!queue.isEmpty()) {
            List<Integer> list = queue.poll();

            if(list.size() == 0) {
                continue;
            }

            for(int v : list) {
                if(!visit[v]) {
                    set.add(v);
                    if(type) {
                        queue.add(front[v]);
                    }
                    else {
                        queue.add(back[v]);
                    }
                    visit[v] = true;
                }
            }
        }
    }
    public static void function(int start) {
        set = new HashSet<>();
        set.add(start);
        visit = new boolean[n+1];
        Queue<List<Integer>> frontQueue = new LinkedList<>();
        Queue<List<Integer>> backQueue = new LinkedList<>();
        frontQueue.add(front[start]);
        backQueue.add(back[start]);

        search(frontQueue, true);
        search(backQueue, false);

        if(set.size() == n) {
            answer++;
        }
    }
}
