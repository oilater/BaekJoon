import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;
    static int N, M;
    static int[] arr;
    static boolean[] visited;
    static int[] picked;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        picked = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        permutation(0);
        System.out.println(sb);
    }

    private static void permutation(int cnt) {
        if (cnt == M ) {
            for (int i = 0; i < M; i++) {
                sb.append(picked[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                picked[cnt] = arr[i];
                permutation(cnt + 1);
                visited[i] = false;
            }
        }
    }
}