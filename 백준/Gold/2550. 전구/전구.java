import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n];

        String[] button = br.readLine().split(" ");
        String[] light = br.readLine().split(" ");

        Link[] links = new Link[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(button[i].equals(light[j])) {
                    links[i] = new Link(j, Integer.parseInt(button[i]));
                    break;
                }
            }
        }

        int next = 0;
        dp[next] = links[next].targetIndex;
        Link[] tracking = new Link[n];
        tracking[next] = new Link(next, links[next].switchNumber);
        for(int i = 1; i < n; i++) {
            if(dp[next] < links[i].targetIndex) {
                dp[++next] = links[i].targetIndex;
                tracking[i] = new Link(next, links[i].switchNumber);
            }
            else {
                int index = binarySearch(links[i].targetIndex, 0, next);
                dp[index] = links[i].targetIndex;
                tracking[i] = new Link(index, links[i].switchNumber);
            }
        }

        List<Integer> answer = new ArrayList<>();
        for(int i = tracking.length-1; i >= 0; i--) {
            if(next == tracking[i].targetIndex) {
                answer.add(tracking[i].switchNumber);
                next--;
            }
        }
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        for(int v : answer) {
            sb.append(v).append(" ");
        }
        System.out.println(answer.size());
        System.out.println(sb);
    }

    public static int binarySearch(int num, int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2;

            if(num <= dp[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static class Link{
        int targetIndex;
        int switchNumber;
        public Link(int targetIndex, int switchNumber) {
            this.targetIndex = targetIndex;
            this.switchNumber = switchNumber;
        }
    }
}