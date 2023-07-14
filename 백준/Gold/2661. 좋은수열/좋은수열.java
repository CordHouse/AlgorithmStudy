import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n;
    private static int[] number = {1, 2, 3};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0, "");
    }

    public static void dfs(int depth, String str) {
        if(depth == n) {
            System.out.println(str);
            System.exit(0);
            return;
        }

        for(int i = 0; i < number.length; i++) {
            if(makeString(str + number[i])) {
                dfs(depth+1, str + number[i]);
            }
        }
    }

    public static boolean makeString(String str) {
        for(int i = 1; i <= str.length() / 2; i++) {
            String cur = str.substring(str.length() - i * 2, str.length() - i);
            String next = str.substring(str.length() - i);
            if(cur.equals(next)) {
                return false;
            }
        }
        return true;
    }
}