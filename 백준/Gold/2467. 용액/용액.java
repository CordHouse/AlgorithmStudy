import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        long[] arr = new long[n];

        for(int i = 0; i < line.length; i++) {
            arr[i] = Long.parseLong(line[i]);
        }

        int start = 0;
        int end = arr.length-1;
        long min = Long.MAX_VALUE;
        long[] answer = new long[2];
        while(start < end) {
            long sum = arr[start] + arr[end];

            if(sum == 0) {
                answer[0] = arr[start];
                answer[1] = arr[end];
                break;
            }
            else {
                if(Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer[0] = arr[start];
                    answer[1] = arr[end];
                }
                if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        Arrays.sort(answer);
        StringBuilder sb = new StringBuilder();
        for(long v : answer) {
            sb.append(v).append(" ");
        }
        System.out.println(sb);
    }
}