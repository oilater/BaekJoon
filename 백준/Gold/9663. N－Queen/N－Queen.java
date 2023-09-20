import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] R;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        R = new int[N+1]; // R 배열 만들기

        findQueen(0);
        System.out.println(cnt);
    }

    private static void findQueen(int n) {
        if (!promising(n)) return;

        if (n == N) {
            cnt++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            R[n+1] = i;
            findQueen(n + 1);
        }
    }

    private static boolean promising(int n) {
        for (int i = 1; i < n; i++) {
            if (R[n] == R[i] || Math.abs(n - i) == Math.abs(R[n] - R[i]))
                return false;
        }
        return true;
    }
}