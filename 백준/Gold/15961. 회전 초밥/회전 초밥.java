import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());  // 회전 초밥 벨트에 놓인 접시의 수
        int d = Integer.parseInt(st.nextToken());  // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken());  // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken());  // 쿠폰 번호

        int[] belt = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[d + 1];  // 초밥 종류별 개수를 저장하는 배열
        int maxCount = 0;  // 손님이 먹을 수 있는 초밥 가짓수의 최댓값

        Set<Integer> sushiSet = new HashSet<>();

        // 초기 윈도우 설정
        for (int i = 0; i < k; i++) {
            count[belt[i]]++;
            sushiSet.add(belt[i]);
        }

        maxCount = sushiSet.size();

        // 슬라이딩 윈도우를 사용하여 초밥 가짓수 계산
        for (int i = 1; i < N; i++) {
            count[belt[i - 1]]--;
            if (count[belt[i - 1]] == 0) {
                sushiSet.remove(belt[i - 1]);
            }

            count[belt[(i + k - 1) % N]]++;
            sushiSet.add(belt[(i + k - 1) % N]);

            int currentCount = sushiSet.size();
            if (count[c] == 0) {
                currentCount++;
            }

            maxCount = Math.max(maxCount, currentCount);
        }

        System.out.println(maxCount);
    }
}