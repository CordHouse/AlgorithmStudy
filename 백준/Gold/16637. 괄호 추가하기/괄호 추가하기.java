import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Character> c = new ArrayList<>();
    private static List<Integer> list = new ArrayList<>();
    private static int len, answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        for(int i = 0; i < n; i++) {
            if(i % 2 == 1) {
                c.add(input.charAt(i));
            }
            else {
                list.add(input.charAt(i) - '0');
            }
        }

        len = n / 2 - 1;
        dfs(0, list.get(0));
        System.out.println(answer);
    }

    public static int cal(char c, int left, int right) {
        if(c == '*') {
            return left * right;
        } else if(c == '+') {
            return left + right;
        } else if(c == '-') {
            return left - right;
        }
        return 0;
    }

    public static void dfs(int idx, int result) {
        if(idx > len) {
            answer = Math.max(answer, result);
            return ;
        }

        // 괄호 없을 때
        int ans = 0;
        ans += cal(c.get(idx), result, list.get(idx+1));
        dfs(idx+1, ans);

        // 괄호 있을 때
        if(idx < c.size()-1) {
            dfs(idx+2, cal(c.get(idx), result, cal(c.get(idx+1), list.get(idx+1), list.get(idx+2))));
        }
    }
}