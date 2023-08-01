import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Cost> queue = new PriorityQueue<>((o1, o2) -> o1.person-o2.person);

        int max = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int person = Integer.parseInt(st.nextToken());
            queue.add(new Cost(money, person));
            max = Math.max(max, person);
        }

        int[] dp = new int[c+100];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        while(!queue.isEmpty()) {
            Cost cost = queue.poll();

            for(int i = cost.person; i < dp.length; i++) {
                if(dp[i-cost.person] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - cost.person] + cost.money);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = c; i < dp.length; i++) {
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);
    }

    static class Cost {
        int money, person;

        public Cost(int money, int person) {
            this.money = money;
            this.person = person;
        }
    }
}