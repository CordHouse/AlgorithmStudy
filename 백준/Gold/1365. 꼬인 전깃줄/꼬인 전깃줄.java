import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] dp;
    private static List<Integer> link = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++) {
                if (j+1 == number) {
                    link.add(j);
                    break;
                }
            }
        }

        int next = 0;
        dp[next] = link.get(next);

        for(int i = 1; i < n; i++) {
            if(dp[next] < link.get(i)) {
                dp[++next] = link.get(i);
            }
            else {
                int index = binarySearch(link.get(i), 0, next);
                dp[index] = link.get(i);
            }
        }

        System.out.println(n - next - 1);
    }

    public static int binarySearch(int index, int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2;

            if(index <= dp[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
}
