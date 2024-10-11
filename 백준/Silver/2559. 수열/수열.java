import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] answer = new int[n-k+1];
        int sum = 0;
        for(int i = 0; i < k; i++) {
            sum += num[i];
        }
        answer[0] = sum;

        int value = answer[0];
        for(int i = 1; i <= n; i++) {
            if(i+k <= n) {
                answer[i] = answer[i - 1] - num[i - 1] + num[(i - 1 + k)];
                value = Math.max(value, answer[i]);
            }
        }
        System.out.println(value);
    }
}
