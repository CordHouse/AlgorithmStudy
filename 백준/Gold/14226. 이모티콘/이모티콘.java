import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static int answer = Integer.MAX_VALUE;
    private static int s;
    private static boolean[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());

        visit = new boolean[s+1][s+1];

        bfs();
        System.out.println(answer);
    }

    public static void bfs() {
        PriorityQueue<Time> queue = new PriorityQueue<>((o1, o2) -> o1.stackTime - o2.stackTime);
        queue.add(new Time(0, 1, 0));
        visit[0][1] = true;

        while(!queue.isEmpty()) {
            Time time = queue.poll();

            if(time.stackS == s) {
                answer = Math.min(answer, time.stackTime);
                break;
            }

            if(time.stackS != 0 && !visit[time.stackS][time.stackS]) {
                queue.add(new Time(time.stackS, time.stackS, time.stackTime + 1)); // 이모티콘 모두 복사
                visit[time.stackS][time.stackS] = true;
            }
            if(time.save > 0 && time.save + time.stackS <= s && !visit[time.save][time.save + time.stackS]) {
                queue.add(new Time(time.save, time.save + time.stackS, time.stackTime + 1)); // 이모티콘 붙여 넣기
                visit[time.save][time.save + time.stackS] = true;
            }
            if(time.stackS - 1 >= 0 && !visit[time.save][time.stackS - 1]) {
                queue.add(new Time(time.save, time.stackS - 1, time.stackTime + 1)); // 이모티콘 하나 삭제
                visit[time.save][time.stackS - 1] = true;
            }
        }
    }

    static class Time {
        int save;
        int stackS;
        int stackTime;
        public Time(int save, int stackS, int stackTime) {
            this.save = save;
            this.stackS = stackS;
            this.stackTime = stackTime;
        }
    }
}