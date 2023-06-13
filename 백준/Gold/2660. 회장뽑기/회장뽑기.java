import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] distance = new int[n+1][n+1]; // 거리 점수를 저장할 변수
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i != j) {
                    distance[i][j] = 100_000_000;
                }
            }
        }

        while(true) {
            String[] line = br.readLine().split(" ");
            int i = Integer.parseInt(line[0]);
            int j = Integer.parseInt(line[1]);
            if(i == -1 && j == -1) {
                break;
            }
            distance[i][j] = 1;
            distance[j][i] = 1;
        }

        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        Map<Integer, PriorityQueue<Integer>> answer = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            int max = 0;
            for(int j = 1; j <= n; j++) {
                max = Math.max(max, distance[i][j]);
            }
            if(!answer.containsKey(max)) {
                answer.put(max, new PriorityQueue<>());
            }
            answer.get(max).add(i);
        }

        List<Integer> list = new ArrayList<>(answer.keySet());
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0)).append(" ").append(answer.get(list.get(0)).size()).append("\n");
        while(!answer.get(list.get(0)).isEmpty()) {
            sb.append(answer.get(list.get(0)).poll()).append(" ");
        }
        System.out.println(sb);
    }
}