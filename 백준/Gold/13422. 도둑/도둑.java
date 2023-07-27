import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());

        for(int i = 0; i < cycle; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            long k = Integer.parseInt(st.nextToken());
            int answer = 0;

            int[] house = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            long sum = 0;
            if(n == m) {
                for(int j = 0; j < m; j++) {
                    sum += house[j];
                }
                if(sum < k) {
                    System.out.println("1");
                }
                else {
                    System.out.println("0");
                }
            }
            else {
                int s = 0;
                int e = 1;
                int count = 0;
                sum = house[0];
                while (s < n) {
                    if (sum >= k || count > m - 1) {
                        sum -= house[s++];
                        count--;
                        continue;
                    }
                    sum += house[e++ % n];
                    count++;
                    if (count == m) {
                        answer++;
                    }
                }
                System.out.println(answer);
            }
        }
    }
}
