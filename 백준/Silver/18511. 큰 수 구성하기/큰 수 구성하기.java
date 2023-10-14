import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;

    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int n = String.valueOf(N).length();
        recursive(0);
        System.out.println(ans);
    }
    private static void recursive (int sum) {
        if (sum > N) return;
        ans = Math.max(ans, sum);

        for (int i = 0; i < K; i++) {
            recursive(sum * 10 + arr[i]);
        }
    }
}