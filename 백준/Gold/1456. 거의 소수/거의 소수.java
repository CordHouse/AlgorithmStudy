import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int answer = 0;
    private static final int SIZE = 100_000_001;
    private static boolean[] prime = new boolean[SIZE];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);

        makePrime();
        for(int i = 2; i < SIZE; i++) {
            if(!prime[i]) {
                searchPrime(i, a, b);
            }
        }
        System.out.println(answer);
    }

    public static void searchPrime(int prime, long min, long max) {
        long num = 0;
        int n = 2;
        while(true) {
            if(num > max) {
                break;
            }
            num = (long) Math.pow(prime, n++);
            if(num >= min && num <= max) {
                answer++;
            }
        }
    }

    public static void makePrime() {
        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i < Math.sqrt(SIZE); i++) {
            if(prime[i]) {
                continue;
            }
            for(int j = i*i; j < SIZE; j+=i) {
                prime[j] = true;
            }
        }
    }
}