import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] arr = new char[n];
        for(int i = 0; i < n; i++) {
            arr[i] = br.readLine().charAt(0);
        }

        int s = 0;
        int e = n-1;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(s <= e) {
            if(count == 80) {
                sb.append("\n");
                count = 0;
            }
            if(arr[s] - '0' < arr[e] - '0') {
                sb.append(arr[s++]);
            }
            else if(arr[s] - '0' > arr[e] - '0') {
                sb.append(arr[e--]);
            }
            else {
                int left = s;
                int right = e;
                while(arr[left] == arr[right]) {
                    if(left < n-1) {
                        left++;
                    }
                    if(right > 0) {
                        right--;
                    }
                    if(arr[left] < arr[right]) {
                        sb.append(arr[s++]);
                    }
                    else if(arr[left] > arr[right]){
                        sb.append(arr[e--]);
                    }
                }
            }
            count++;
        }

        System.out.println(sb);
    }
}
