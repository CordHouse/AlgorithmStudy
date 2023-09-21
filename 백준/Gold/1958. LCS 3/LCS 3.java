import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split("");
        String[] second = br.readLine().split("");
        String[] thread = br.readLine().split("");

        lcs(first, second, thread);
        System.out.println(answer);
    }

    public static void lcs(String[] s1, String[] s2, String[] s3) {
        int[][][] map = new int[s1.length+1][s2.length+1][s3.length+1];

        for(int i = 1; i <= s1.length; i++) {
            for(int j = 1; j <= s2.length; j++) {
                for(int k = 1; k <= s3.length; k++) {
                    if(s1[i-1].equals(s2[j-1]) && s1[i-1].equals(s3[k-1])) {
                        map[i][j][k] = map[i - 1][j - 1][k - 1] + 1;
                    }
                    else {
                        map[i][j][k] = Math.max(map[i-1][j][k], Math.max(map[i][j-1][k], map[i][j][k-1]));
                    }
                }
            }
        }
        answer = map[s1.length][s2.length][s3.length];
    }
}