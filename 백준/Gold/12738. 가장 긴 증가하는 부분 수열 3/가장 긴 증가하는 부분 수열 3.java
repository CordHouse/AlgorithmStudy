import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> bs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken()) + 1000000000;
            if (bs.isEmpty() || bs.get(bs.size()-1) < cur) {
                bs.add(cur);
                continue;
            }
            int idx = Collections.binarySearch(bs, cur);
            int targetIdx = idx>=0?idx:-idx-1;
            bs.set(targetIdx, cur);
        }
        System.out.println(bs.size());
    }
}