import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K; // 물품의 수 N, 준서가 버틸 수 있는 무게 K
    static int[][] dp;
    static int[] weights;
    static int[] values;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weights = new int[N+1];
        values = new int[N+1];
        dp = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken()); // 각 물건의 무게
            values[i] = Integer.parseInt(st.nextToken()); // 각 물건의 가치
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (weights[i] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i]] + values[i]);
            }
        }
        System.out.println(dp[N][K]);
    }
}