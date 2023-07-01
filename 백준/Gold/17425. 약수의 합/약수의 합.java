import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static long[] fx, gx;
    private static final int SIZE = 1_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        fx = new long[SIZE];
        gx = new long[SIZE];

        Arrays.fill(fx, 1);
        make();

        for(int i = 1; i < SIZE; i++) {
            gx[i] += gx[i-1] + fx[i];
        }

        for(int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(gx[num]).append("\n");
        }
        System.out.println(sb);
    }
    public static void make() {
        for(int i = 2; i < SIZE; i++) {
            for(int j = 1; i*j < SIZE; j++) {
                fx[i*j] += i;
            }
        }
    }
}