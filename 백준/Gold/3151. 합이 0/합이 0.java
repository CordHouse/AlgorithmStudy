import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long answer = 0;

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for(int i = 0; i < n; i++) {
            
            if(arr[i] > 0) {
                break;
            }
            int s = i+1; // 초기 값
            int e = n-1; // 끝 값

            while(s < e) {
                int left = 1;
                int right = 1;
                int sum = arr[i] + arr[s] + arr[e];

                if(sum == 0) {
                    if(arr[s] == arr[e]) {
                        answer += count(e - s + 1);
                        break;
                    }

                    
                    while(s + 1 < e && arr[s] == arr[s+1]) {
                        left++;
                        s++;
                    }

                    
                    while(s < e - 1 && arr[e] == arr[e-1]) {
                        right++;
                        e--;
                    }

                    answer += ((long) left * right);
                }

                if(sum > 0) {
                    e--;
                }
                else {
                    s++;
                }
            }
        }
        System.out.println(answer);
    }

    public static int count(int n) {
        return n * (n-1) / 2;
    }
}