import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static int[] dp;
    static final int MAX = 987_645_321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[K+1];
        for (int i = 1; i < K+1; i++) {
            dp[i] = MAX;
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                if (i - arr[j] >= 0)
                    dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
            }
        }
        int ans = dp[K] == MAX ? -1 : dp[K];
        System.out.println(ans);
    }
}