import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        while(testcase --> 0) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = input[0];
            int m = input[1];
            int[] number = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Queue<Index> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int count = 0;

            for(int i = 0; i < n; i++) {
                queue.add(new Index(i, number[i]));
                if(i != m && number[m] < number[i]) {
                    pq.add(number[i]);
                }
            }

            while(!queue.isEmpty()) {
                Index value = queue.poll();

                if(!pq.isEmpty()) {
                    if(pq.peek() == value.priority) {
                        pq.poll();
                        count++;
                    }
                    else {
                        queue.add(value);
                    }
                }
                else {
                    if (value.index == m) {
                        count++;
                        break;
                    }
                    else if(value.priority == number[m]) {
                        count++;
                    }
                    else {
                        queue.add(value);
                    }
                }
            }
            System.out.println(count);
        }
    }

    static class Index {
        int index;
        int priority;

        public Index(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}
