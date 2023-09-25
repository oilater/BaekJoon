import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static List<int[]> steps;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        steps = new ArrayList<>();
        // 출력
        BigInteger cnt = new BigInteger("2");
        sb.append(cnt.pow(N).subtract(new BigInteger("1"))).append('\n');
        if (N <= 20) {
            hanoi(N, 1, 2, 3);
            for (int[] arr : steps) {
                sb.append(arr[0]).append(' ').append(arr[1]).append('\n');
            }
        }
                System.out.println(sb);
    }

        private static void hanoi(int n, int from, int tmp, int to) {

            if (n == 1) {
                steps.add(new int[] {from, to});
                return;
            }

            hanoi(n-1, from, to, tmp);
            steps.add(new int[] {from, to});
        hanoi(n-1, tmp, from, to);
    }
}