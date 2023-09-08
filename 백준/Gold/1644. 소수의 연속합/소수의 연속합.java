import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MAX = 4_000_001;
    private static int goal, sum;
    private static int answer = 0;
    private static boolean[] prime = new boolean[MAX];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        goal = Integer.parseInt(br.readLine());

        if(goal == 2 || goal == 3) {
            System.out.println(1);
            return;
        }
        if(goal == 5) {
            System.out.println(2);
            return;
        }

        makePrime();

        int s = 2;
        int e = 3;
        int tmp = 0;
        sum = s + e;
        while(s < e && e < MAX-1) {
            if(s == goal) {
                break;
            }
            if(sum == goal && tmp != 0) {
                answer++;
            }
            if(sum < goal) {
                tmp = searchPrime(++e);
                sum += tmp;
            }
            else {
                tmp = searchPrime(s++);
                sum -= tmp;
            }
        }
        System.out.println(answer);
    }

    public static int searchPrime(int num) {
        if(!prime[num]) {
            return num;
        }
        return 0;
    }
    public static void makePrime() {
        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i < Math.sqrt(MAX); i++) {
            for(int j = i*i; j < MAX; j+=i) {
                if(!prime[j]) {
                    prime[j] = true;
                }
            }
        }
    }
}