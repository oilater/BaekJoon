import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T;
    static int N, B;
    static int[] arr;
    static boolean[] picked;
    static int minHeight = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            minHeight = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            picked = new boolean[N];
            powerSet(0);

            sb.append("#").append(tc).append(' ').append(minHeight - B).append('\n');
        }
        System.out.println(sb);
    }

    private static void powerSet(int cnt) {


        if (cnt == N) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (picked[i]) {
                    sum += arr[i];
                }
            }
            if (sum >= B && sum < minHeight) {
                minHeight = sum;
            }
            return;
        }

        picked[cnt] = true;
        powerSet(cnt + 1);
        picked[cnt] = false;
        powerSet(cnt + 1);
    }
}