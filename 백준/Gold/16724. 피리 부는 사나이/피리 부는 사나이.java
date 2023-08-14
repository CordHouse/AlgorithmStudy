import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;
    private static char[][] map;
    private static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        parent = new int[n * m];

        for(int i = 0; i < n*m; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int index = i * m + j;
                int nextIndex = searchIndex(i, j);

                int cur = find(index);
                int next = find(nextIndex);

                if(cur != next) {
                    union(cur, next);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < parent.length; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }

    public static void union(int cur, int next) {
        if(cur < next) {
            parent[next] = cur;
        } else {
            parent[cur] = next;
        }
    }

    public static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }

    public static int searchIndex(int i, int j) {
        if(map[i][j] == 'U') {
            i-=1;
        }
        else if(map[i][j] == 'D') {
            i+=1;
        }
        else if(map[i][j] == 'L') {
            j-=1;
        }
        else {
            j+=1;
        }
        return i * m + j;
    }
}

