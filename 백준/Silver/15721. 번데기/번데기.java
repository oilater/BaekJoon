import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 문제
 * 뻔 데기 뻔 데기 뻔 뻔 데기 데기 n-1 => * n번
 * 생각나는 풀이
 *
 *
 */
public class Main {
    static int A, T, N;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        int cnt = 0;
        while (cnt <= 150) {
            cnt++;
            // 뻔 데기 뻔 데기
            for (int i = 0; i < 4; i++) {
                if (i % 2 == 0) list.add(0);
                else list.add(1);
            }

            // 뻔 * n
            for (int i = 1; i <= cnt + 1; i++) {
                list.add(0);
            }

            // 데기 * n
            for (int i = 1; i <= cnt + 1; i++) {
                list.add(1);
            }
        }

        int tmpCnt = 0;
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            if (num == N) tmpCnt++;

            if (tmpCnt == T) {
                System.out.println(i % A);
                return;
            }
        }

    }
}