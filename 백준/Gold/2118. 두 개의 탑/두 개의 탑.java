import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        int sum = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        int start = 0;
        int end = 1;
        int answer = 0;
        int current = arr[start];

        while(start < end && end < n) {
            int min = Integer.min(current, sum-current);

            answer = Integer.max(answer, min);

            if(current == min) {
                current += arr[end++];
            }
            else {
                current -= arr[start];
                start++;
            }
        }

        System.out.println(answer);
    }
}