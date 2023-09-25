import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[] num;
    private static int[] lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        lis = new int[num.length+1];
        int next = 0;
        int answer = 0;
        lis[0] = num[0];

        for(int i = 1; i < n; i++) {
            if(lis[next] < num[i]) {
                lis[++next] = num[i];
            }
            else {
                int index = search(0, next, num[i]);
                lis[index] = num[i];
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static int search(int start, int end, int target) {
        while(start < end) {
            int mid = (start + end) / 2;

            if(lis[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return end;
    }
}