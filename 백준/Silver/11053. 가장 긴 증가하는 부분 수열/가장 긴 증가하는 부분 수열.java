import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int N;
    static int dp[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) { // 배열 입력 받기
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dp, 1); // 배열 초기화
        for (int i = 1; i < N; i++) { // 1 ~ N
            for (int j = 0; j < i; j++) { // 0 ~ i
                if (arr[j] < arr[i] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 0;
        for (int el : dp) {
            max = Math.max(el, max);
        }

        System.out.println(max);
    }
}