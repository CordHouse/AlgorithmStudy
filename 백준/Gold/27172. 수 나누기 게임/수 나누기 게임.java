import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int n;
    private static int[] point, card;
    private static final int MAX = 1_000_001;
    private static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        point = new int[MAX];
        prime = new boolean[MAX];

        card = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int c : card) {
            prime[c] = true;
        }

        makePrime();

        StringBuilder sb = new StringBuilder();
        for(int c : card) {
            sb.append(point[c]).append(" ");
        }
        System.out.println(sb);
    }
    public static void makePrime() {
        prime[0] = true;
        prime[1] = true;

        for(int c : card) {
            for (int j = c; j < MAX; j += c) {
                if(prime[j]) {
                    point[c]++;
                    point[j]--;
                }
            }
        }
    }
}
