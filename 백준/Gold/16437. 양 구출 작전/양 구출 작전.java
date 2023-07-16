import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] countList;
    private static char[] animalList;
    private static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        countList = new int[n+1];
        animalList = new char[n+1];
        list = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            char animal = st.nextToken().charAt(0);
            int count = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            animalList[i] = animal;
            countList[i] = count;
            list[num].add(i);
        }
        System.out.println(postOrder(1));
    }

    public static long postOrder(int start) {
        long answer = 0;

        for(int next : list[start]) {
            answer += postOrder(next);
        }

        if(animalList[start] == 'S') {
            return answer + countList[start];
        }
        return Math.max(answer - countList[start], 0);
    }
}