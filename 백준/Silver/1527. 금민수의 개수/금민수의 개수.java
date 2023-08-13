import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  A~B 다 돌면 시간초과 난다
 *  4 7 44 47 74 77 444 447 474 744 747  774  777 4444 4447   4474   4477 ...
 *  4와 7을 이용해 숫자를 만든 후 이게 범위에 있는지 확인해야 할 것 같다
 *  4와 7을 이용해 만들 수 있는 순열 =>
 *  A B / AA AB BA BB / AAA AAB ABA ABB BAA BAB BBA BBB /
 *  각 개수로 이루어진 중복순열
 *
 *
 */
public class Main {
    static long A, B;
    static int[] picked;
    static long gmCnt;
    static int[] arr = new int[] {4, 7};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        for (int i = String.valueOf(A).length(); i <= String.valueOf(B).length(); i++) {
            picked = new int[i];
            permutation(0, i);
        }
        System.out.println(gmCnt);
    }

    private static void permutation(int cnt, int idx) {
        if (cnt == idx) {
            String str = "";
            for (int i = 0; i < picked.length; i++) {
                str += picked[i];
            }
            long strToInt = Long.parseLong(str);
            if (strToInt >=  A && strToInt <= B) {
                gmCnt++;
            }
            return;
        }

        for (int i = 0; i < 2; i++) {
            picked[cnt] = arr[i];
            permutation(cnt+1, idx);
        }
    }
}