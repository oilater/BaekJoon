import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합으로 풀자 nCm
public class Main {
    static int N, M;
    static int[] arr;
    static int[] picked;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i+1;
        }
        picked = new int[M];

        combination(0, 0);
        System.out.println(sb);
    }

    static void combination(int start, int cnt) {
        if (cnt == M) {
            for (int el : picked) {
                sb.append(el).append(' ');
            }
            sb.append('\n');
            return;

        }
        for (int i = start; i < N; i++) {
            picked[cnt] = arr[i];
            combination(i+1, cnt+1);
        }
    }
}